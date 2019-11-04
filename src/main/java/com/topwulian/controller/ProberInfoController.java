package com.topwulian.controller;


import com.topwulian.model.ProberDeviceInfo;
import com.topwulian.model.ProberRequestInfo;
import com.topwulian.model.ProberUserInfo;
import com.topwulian.model.RespEntiry;
import com.topwulian.service.ProberDeviceInfoSerivce;
import com.topwulian.service.ProberUserInfoService;
import net.bytebuddy.asm.Advice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prober")
public class ProberInfoController {


    //胶体金
    //农残检测

    @Autowired
    private ProberUserInfoService proberUserInfoService;

    @Autowired
    private ProberDeviceInfoSerivce proberDeviceInfoSerivce;

    @RequestMapping("/info")
    public Object info(){
        JSONArray jsonArray = new JSONArray();

        JSONObject json1 = new JSONObject();
        json1.put("CheckName","基地");
        json1.put("testItem","");
        json1.put("sampleName","");
        json1.put("detDate","");
        json1.put("testCom","1");



        JSONObject json2 = new JSONObject();
        json2.put("CheckName","乡镇");
        json2.put("sampleName","样品名称");
        json2.put("testItem","检测项目");
        json1.put("testCom","2");


        JSONObject json3 = new JSONObject();
        json3.put("CheckName","市县");
        json3.put("testValueCom","检测值单位");
        json3.put("inspector","检测人员");
        json1.put("testCom","3");


        jsonArray.add(json1);
        jsonArray.add(json2);
        jsonArray.add(json3);

        //return RespEntiry.success("获取成功!", jsonArray.toString());
        return jsonArray.toString();
    }



    @PostMapping("/getUser")
    public Object getUser(@RequestBody ProberRequestInfo info){

        System.out.println("账户:"+info.getUsername());

        JSONObject json = new JSONObject();
        json.put("id",System.currentTimeMillis()+"");
        if(info.getUsername().equals("")||info.getPassword().equals("")){
            json.put("code","1002");
            json.put("msg","参数不正确");
            json.put("token","");
        }else{

            /**
             * 根据用户名账号密码查询是否存在此账号
             */
            List<ProberUserInfo> userInfoList = proberUserInfoService.getByUserInfo(info.getUsername());
            if(userInfoList.size()>0){
                //验证账号密码是否正确
                String passwrod =  userInfoList.get(0).getPassword();//查询 list 列表中的第一个密码
                if(info.getPassword().equalsIgnoreCase(passwrod)){

                    /**
                     * 生成token
                     */
                    //String token1 = UUID.randomUUID().toString().replaceAll("-", "");
                    //String token2 = UUID.randomUUID().toString().replaceAll("-", "");
                    json.put("code","1000");
                    json.put("msg","操作成功");
                    json.put("token",userInfoList.get(0).getToken());
                }else{
                    json.put("code","1009");
                    json.put("msg","密码错误");
                    json.put("token","");
                }
            }else{
                //不存在此账号
                json.put("code","1008");
                json.put("msg","账户不存在");
                json.put("token","");
            }


        }

        return json.toString();
    }

    @PostMapping("/uploadDataDock")
    public Object uploadDataDock(@RequestBody ProberRequestInfo info){
        JSONArray resultJson = new JSONArray();
        if(info.getToken()=="" || info.getData()==""){
            JSONObject json = new JSONObject();
            json.put("code","1001");
            json.put("msg","某参数不能为空");
            json.put("count",0);
            json.put("CheckID","");
        }else{
            System.out.println("获取数据:"+info.getData());
            System.out.println("获取token:"+info.getToken());


            //获取数据成功. 判断token是属于哪个基地
            List<ProberUserInfo> proberUserInfos = proberUserInfoService.getByTokenInfo(info.getToken());
            if(proberUserInfos.size()>0){
                //判断token是否有效
                if(info.getToken().equalsIgnoreCase(proberUserInfos.get(0).getToken())) {
                    String framid = proberUserInfos.get(0).getFarmId();
                    if(info.getData() != "") {
                        JSONArray jsonArray = JSONArray.fromObject(info.getData());
                        if (jsonArray.size() > 0) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                String CheckID = jsonArray.getJSONObject(i).get("CheckID").toString();


                                JSONObject json = new JSONObject();
                                json.put("code", "1000");
                                json.put("msg", "操作成功");
                                json.put("count", "1");
                                json.put("CheckID", CheckID);
                                resultJson.add(json);


                                ProberDeviceInfo proberDeviceInfo = new ProberDeviceInfo();

                                proberDeviceInfo.setCheckID(CheckID);
                                proberDeviceInfo.setFarmid(framid);


                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                                String testItem = jsonArray.getJSONObject(i).get("testItem").toString();
                                String sampleName = jsonArray.getJSONObject(i).get("sampleName").toString();
                                String detDate = jsonArray.getJSONObject(i).get("detDate").toString();
                                String detResult = jsonArray.getJSONObject(i).get("detResult").toString();
                                String testMethod = jsonArray.getJSONObject(i).get("testMethod").toString();
                                String testBasis = jsonArray.getJSONObject(i).get("testBasis").toString();
                                String testValueCom = jsonArray.getJSONObject(i).get("testValueCom").toString();
                                String testCom = jsonArray.getJSONObject(i).get("testCom").toString();
                                String inspector = jsonArray.getJSONObject(i).get("inspector").toString();
                                String auditors = jsonArray.getJSONObject(i).get("auditors").toString();
                                String referValue = jsonArray.getJSONObject(i).get("referValue").toString();
                                if (StringUtils.isNotBlank(testItem)) {
                                    proberDeviceInfo.setTestItem(testItem);
                                }
                                if (StringUtils.isNotBlank(sampleName)) {
                                    proberDeviceInfo.setSampleName(sampleName);
                                }
                                if (StringUtils.isNotBlank(detDate)) {
                                    try {
                                        Date date = df.parse(detDate);
                                        proberDeviceInfo.setDetDate(date);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (StringUtils.isNotBlank(detResult)) {
                                    proberDeviceInfo.setDetResult(detResult);
                                }
                                if (StringUtils.isNotBlank(testMethod)) {
                                    proberDeviceInfo.setTestMethod(testMethod);
                                }
                                if (StringUtils.isNotBlank(testBasis)) {
                                    proberDeviceInfo.setTestBasis(testBasis);
                                }
                                if (StringUtils.isNotBlank(testValueCom)) {
                                    proberDeviceInfo.setTestValueCom(testValueCom);
                                }
                                if (StringUtils.isNotBlank(testCom)) {
                                    proberDeviceInfo.setTestCom(testCom);
                                }
                                if (StringUtils.isNotBlank(inspector)) {
                                    proberDeviceInfo.setInspector(inspector);
                                }
                                if (StringUtils.isNotBlank(auditors)) {
                                    proberDeviceInfo.setAuditors(auditors);
                                }
                                if (StringUtils.isNotBlank(referValue)) {
                                    proberDeviceInfo.setReferValue(referValue);
                                }


                                proberDeviceInfo.setCreateuser("admin");
                                proberDeviceInfo.setUpdateuser("admin");

                                proberDeviceInfoSerivce.save(proberDeviceInfo);


                            }
                        }
                    }
                }else{
                    JSONObject json = new JSONObject();
                    json.put("code", "1007");
                    json.put("msg", "用户TOKEN失效");
                    json.put("count", 0);
                    json.put("CheckID", "");
                    resultJson.add(json);
                }

            }else{
                JSONObject json = new JSONObject();
                json.put("code", "1007");
                json.put("msg", "用户TOKEN失效");
                json.put("count", 0);
                json.put("CheckID", "");
                resultJson.add(json);
            }
        }
        return resultJson;
    }



    public static void main(String[] args) throws ParseException {
        String sss  = "2019-07-26 13:38:42";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = df.parse(sss);

        System.out.println(date);
    }



}
