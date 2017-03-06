package com.unicom.fmos.service.sys.impl;

import com.unicom.fmos.dao.business.InstanceDao;
import com.unicom.fmos.dao.business.MachineFeatureDetailDao;
import com.unicom.fmos.dao.business.MachineFeatureHeadDao;
import com.unicom.fmos.dao.business.ModelDao;
import com.unicom.fmos.dao.sys.DirectionaryDao;
import com.unicom.fmos.dto.DirectionaryDto;
import com.unicom.fmos.dto.SimilarSearchDto;
import com.unicom.fmos.entity.business.Instance;
import com.unicom.fmos.entity.business.MachineFeatureDetail;
import com.unicom.fmos.entity.business.MachineFeatureHead;
import com.unicom.fmos.entity.business.Model;
import com.unicom.fmos.entity.sys.DirectionaryDetail;
import com.unicom.fmos.service.sys.SimilarSearchService;
import com.unicom.fmos.utils.UtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaojb on 2017/2/5.
 */
@Service
public class SimilarSearchServiceImpl implements SimilarSearchService {

    @Autowired
    private InstanceDao instanceDao;
    @Autowired
    private ModelDao modelDao;
    @Autowired
    private DirectionaryDao directionaryDao;
    @Autowired
    private MachineFeatureHeadDao machineFeatureHeadDao;
    @Autowired
    private MachineFeatureDetailDao machineFeatureDetailDao;

    @Override
    public String allInstances() {
        Instance condition = new Instance();
        condition.setActive(true);
        List<Instance> instancesResult = instanceDao.select(condition);
        List<Map<String, String>> maps = new ArrayList<>();
        for (Instance instance : instancesResult) {
            Model modelCondition = new Model();
            modelCondition.setLineId(instance.getModelLineId());
            Model modelResult = modelDao.selectSelective(modelCondition).get(0);
            String model = modelResult.getModelModel();
            String codeModelGroup = modelResult.getModelNumber().substring(0,4);
            DirectionaryDetail directionaryDetailCondition = new DirectionaryDetail();
            directionaryDetailCondition.setDirectionaryKey(codeModelGroup);
            List<DirectionaryDetail> directionaryDetails = directionaryDao.selectDirectionaryDetail(directionaryDetailCondition);
            Map<String, String> tmpMap = new HashMap<>();
            tmpMap.put("id",instance.getLineId().toString());
            tmpMap.put("name",instance.getInstanceName());
            tmpMap.put("model",model);
            if (directionaryDetails.isEmpty()) {
                tmpMap.put("group","未知");
            } else {
                tmpMap.put("group",directionaryDetails.get(0).getDirectionaryValue());
            }

            maps.add(tmpMap);
        }
        return UtilFactory.toJson(maps);
    }

    @Override
    public String instancefeture(Instance instance) {
        Instance condition = new Instance();
        condition.setActive(true);
        condition.setLineId(instance.getLineId());
        List<Instance> instancesResult = instanceDao.select(condition);
        List<DirectionaryDto>  directionaryDtos = machineFeatureHeadDao.getModelFeature(instancesResult.get(0));
        return UtilFactory.toJson(directionaryDtos);
    }

    @Override
    public String search(SimilarSearchDto similarSearchDto) {

        //1.获取目标实例
        Instance targetInstanceCondition = new Instance();
        targetInstanceCondition.setLineId(similarSearchDto.getInstanceLineId());
        targetInstanceCondition.setActive(true);
        Instance targetInstance = instanceDao.select(targetInstanceCondition).get(0);

        //2.根据是否相同类型,是否相同级别初步筛选实例
        List<Instance> referencedList = new ArrayList<>();
        //2.1获取该实例的型号
        Model targetModelCondition = new Model();
        targetModelCondition.setLineId(targetInstance.getModelLineId());
        Model targetModel = modelDao.selectSelective(targetModelCondition).get(0);
        if (similarSearchDto.getSameGroup() == true) {
            Model modelSameModelCondition = new Model();
            modelSameModelCondition.setModelNumber(targetModel.getModelNumber().substring(0,4));
            List<Model> sameModelList = modelDao.selectSameModel(modelSameModelCondition);
            List<Integer> modelLineIdList = new ArrayList<>();
            for (Model model : sameModelList) {
                modelLineIdList.add(model.getLineId());
            }
            if (modelLineIdList.size() == 0) {
                modelLineIdList.add(-1);
            }
            Map<String,Object> mapParam = new HashMap<>();
            mapParam.put("idList",modelLineIdList);
            referencedList = instanceDao.selectInstanceByModelLineId(mapParam);
        }
        else if (similarSearchDto.getSameModel() == true) {
            Model modelSameGroupCondition = new Model();
            modelSameGroupCondition.setModelNumber(targetModel.getModelNumber().substring(0,2));
            List<Model> sameGroupList = modelDao.selectSameModel(modelSameGroupCondition);
            List<Integer> modelLineIdList = new ArrayList<>();
            for (Model model : sameGroupList) {
                modelLineIdList.add(model.getLineId());
            }
            if (modelLineIdList.size() == 0) {
                modelLineIdList.add(-1);
            }
            Map<String,Object> mapParam = new HashMap<>();
            mapParam.put("idList",modelLineIdList);
            referencedList = instanceDao.selectInstanceByModelLineId(mapParam);
        } else {
            targetInstanceCondition = new Instance();
            targetInstanceCondition.setActive(true);
            referencedList = instanceDao.select(targetInstanceCondition);
        }

        //3.将其中的目标实例删去
        for (int i = 0; i < referencedList.size(); i++) {
            if (referencedList.get(i).getLineId() == targetInstance.getLineId()) {
                referencedList.remove(i);
                break;
            }
        }

        //4.将上述找到的实例装入map中
        Map<Integer, Instance> instanceMap = new HashMap<>();
        for(Instance instance : referencedList) {
            instanceMap.put(instance.getLineId(),instance);
        }

        //5.当参数对照表不为空的时候,获取参数
        List<Integer> featureIdArr = similarSearchDto.getFeatureSeled();
        if (featureIdArr != null && featureIdArr.size() != 0) {
            //5.1.获取对比实例的所有参数
            Map<Integer,Map<Integer, BigDecimal>> referenceedInstanceParamsMap = new HashMap<>();
            for (Instance instance : referencedList) {
                Map<Integer, BigDecimal> tmpReferenceedInstanceParamsMap = new HashMap<>();
                for (Integer id : featureIdArr) {
                    //id语义为td_machine_feature_head的主键序号
                    //先确认该model是否该特征参数启用
                    MachineFeatureHead machineFeatureHeadCondition = new MachineFeatureHead();
                    machineFeatureHeadCondition.setModelLineId(instance.getModelLineId());
                    machineFeatureHeadCondition.setLineId(id);
                    machineFeatureHeadCondition.setActive(true);
                    if (machineFeatureHeadDao.select(machineFeatureHeadCondition).size() != 0){
                        MachineFeatureDetail machineFeatureDetailCondition = new MachineFeatureDetail();
                        machineFeatureDetailCondition.setActive(true);
                        machineFeatureDetailCondition.setFeatureHeadLineId(id);
                        machineFeatureDetailCondition.setInstanceLineId(instance.getLineId());
                        List<MachineFeatureDetail> machineFeatureDetailList = machineFeatureDetailDao.select(machineFeatureDetailCondition);
                        if (machineFeatureDetailList.size() != 0) {
                            tmpReferenceedInstanceParamsMap.put(id, machineFeatureDetailList.get(0).getInfoValue());
                        } else {
                            tmpReferenceedInstanceParamsMap.put(id,new BigDecimal(-1));
                        }
                    } else {
                        tmpReferenceedInstanceParamsMap.put(id,new BigDecimal(-1));
                    }
                }
                referenceedInstanceParamsMap.put(instance.getLineId(),tmpReferenceedInstanceParamsMap);
            }

            //6.获取目标实例的所有参数
            Map<Integer, BigDecimal> targetInstanceParamsMap = new HashMap<>();
            for (Integer id : featureIdArr) {
                //id语义为td_machine_feature_head的主键序号
                //先确认该model是否该特征参数启用
                MachineFeatureHead machineFeatureHeadCondition = new MachineFeatureHead();
                machineFeatureHeadCondition.setLineId(id);
                machineFeatureHeadCondition.setActive(true);
                machineFeatureHeadCondition.setModelLineId(targetInstance.getModelLineId());
                if (machineFeatureHeadDao.select(machineFeatureHeadCondition).size() != 0){
                    MachineFeatureDetail machineFeatureDetailCondition = new MachineFeatureDetail();
                    machineFeatureDetailCondition.setInstanceLineId(targetInstance.getLineId());
                    machineFeatureDetailCondition.setActive(true);
                    machineFeatureDetailCondition.setFeatureHeadLineId(id);
                    List<MachineFeatureDetail> machineFeatureDetailList = machineFeatureDetailDao.select(machineFeatureDetailCondition);
                    if (machineFeatureDetailList.size() != 0) {
                        targetInstanceParamsMap.put(id, machineFeatureDetailList.get(0).getInfoValue());
                    } else {
                        targetInstanceParamsMap.put(id,new BigDecimal(-1));
                    }
                } else {
                    targetInstanceParamsMap.put(id,new BigDecimal(-1));
                }
            }

            //7.当特征参数表不为空且需要对比的时候,进行参数大小对比筛选
            if (similarSearchDto.getFeatureSeled() != null && similarSearchDto.getFeatureSeled().size() != 0 && similarSearchDto.getCompared() != 0) {
                if (similarSearchDto.getCompared() == 1) {
                    //参数高于所选实例
                    for (int i = 0;i< referencedList.size();i++) {
                        Integer instanceLineId = referencedList.get(i).getLineId();
                        Map<Integer, BigDecimal> referencedParams = referenceedInstanceParamsMap.get(instanceLineId);
                        for (Integer id : featureIdArr) {
                            BigDecimal targetValue = targetInstanceParamsMap.get(id);
                            BigDecimal referencedValue = referencedParams.get(id);
                            if (referencedValue.compareTo(targetValue) < 0) {
                                //从实例表以及参数表中删去该实例
                                referenceedInstanceParamsMap.remove(instanceLineId);
                                referencedList.remove(i);
                                i = i - 1;
                                break;
                            }
                        }
                    }
                }
                else {
                    //参数低于所选实例
                    for (int i = 0;i< referencedList.size();i++) {
                        Integer instanceLineId = referencedList.get(i).getLineId();
                        Map<Integer, BigDecimal> referencedParams = referenceedInstanceParamsMap.get(instanceLineId);
                        for (Integer id : featureIdArr) {
                            BigDecimal targetValue = targetInstanceParamsMap.get(id);
                            BigDecimal referencedValue = referencedParams.get(id);
                            if (referencedValue.compareTo(targetValue) > 0) {
                                //从实例表以及参数表中删去该实例
                                referenceedInstanceParamsMap.remove(instanceLineId);
                                referencedList.remove(i);
                                i = i - 1;
                                break;
                            }
                        }
                    }
                }
            }

            //8.当特征参数表不为空且个数大于一且对比实例集合还有大于0的实例存在的时候,进行模糊聚类分析
            if (similarSearchDto.getFeatureSeled() != null && similarSearchDto.getFeatureSeled().size() > 1 && referencedList.size() != 0) {
                double[][] parmasArr = new double[referencedList.size() + 1][featureIdArr.size()];
                for (int m = 0; m < featureIdArr.size(); m++) {
                    parmasArr[0][m] = targetInstanceParamsMap.get(featureIdArr.get(m)).doubleValue();
                }
                for (int i = 1; i < referencedList.size() + 1; i++) {
                    Map<Integer,BigDecimal> tmpReferencedMap = referenceedInstanceParamsMap.get(referencedList.get(i-1).getLineId());
                    for (int j = 0; j < featureIdArr.size(); j++) {
                        parmasArr[i][j] = tmpReferencedMap.get(featureIdArr.get(j)).doubleValue();
                    }
                }
                Map<String,Object> resultMap = UtilFactory.clusterAnalysis(parmasArr, similarSearchDto.getThreshold().doubleValue());
                ArrayList<Integer[]> clusterResult = (ArrayList<Integer[]>) resultMap.get("clusterResult");
                double maxThreadHold = (double) resultMap.get("maxThreadHold");
                double minThreadHold = (double) resultMap.get("minThreadHold");
                List<Instance> returnInstanceList = new ArrayList<>();
                Integer[] finalInstanceIndexArr = clusterResult.get(0);
                for (int k = 0; k < finalInstanceIndexArr.length; k++) {
                    if (finalInstanceIndexArr[k] != 0) {
                        returnInstanceList.add(referencedList.get(finalInstanceIndexArr[k] - 1));
                    }
                }
                Map<String,Object> finalMap = new HashMap<>();
                finalMap.put("instanceList", returnInstanceList);
                finalMap.put("maxThreadHold", maxThreadHold);
                finalMap.put("minThreadHold", minThreadHold);
                return UtilFactory.toJson(finalMap);
            }

            //9.否则直接输出
            else {
                Map<String,Object> finalMap = new HashMap<>();
                finalMap.put("instanceList", referencedList);
                return UtilFactory.toJson(finalMap);
            }
        }
        else {
            Map<String,Object> finalMap = new HashMap<>();
            finalMap.put("instanceList", referencedList);
            return UtilFactory.toJson(finalMap);
        }

    }

    @Override
    public String modelByLineId(Instance instance) {
        Model modelCondition = new Model();
        modelCondition.setLineId(instance.getModelLineId());
        modelCondition.setActive(true);
        return UtilFactory.toJson(modelDao.selectSelective(modelCondition).get(0));
    }
}
