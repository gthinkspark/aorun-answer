//package com.aorun.answer.util.jsonp;
//
//import java.util.HashMap;
//
///**
// * Created by duxihu on 14-10-31.
// */
//public class Jsonp {
//
//	private java.util.Map<String, Object> jsonp;
//
//	private Jsonp() {
//		jsonp = new HashMap<String, Object>();
//	}
//
//	private Jsonp(String code, String msg) {
//		jsonp = new HashMap<String, Object>();
//		setResponseCode(code);
//		setMsg(msg);
//	}
//
//	public String getResponseCode() {
//		return (String) jsonp.get("response_code");
//	}
//
//	public Jsonp setResponseCode(String code) {
//		if (null == code)
//			jsonp.put("response_code", "");
//		else
//			jsonp.put("response_code", code);
//		return this;
//	}
//
//	public String getMsg() {
//		return (String) jsonp.get("msg");
//	}
//
//	public Jsonp setMsg(String msg) {
//		if (null == msg)
//			jsonp.put("msg", "");
//		else {
//			jsonp.put("msg", msg);
//		}
//		return this;
//	}
//
////	public static final String SUCCESS_CODE = "0";// 获取数据成功状态码
////	public static final String SUCCESS_CODE_NEWS = "200";// 玉门新闻同步成功响应值
////	public static final String FOREIGN_BIZ_API_SUCCESS_CODE = "200";// 对外业务API接口任务处理成功
////	public static final String NO_LOGIN_CODE = "201";// 请先登录
////	public static final String PARAM_ERROR_CODE = "300";// 参数传递错误状态码
////	public static final String BUSINESS_TIPS_CODE = "400"; // 业务提示状态码
////	public static final String ERROR_CODE = "500"; // 获取数据出错状态码
//
//	// code=0
//	public static Jsonp success() {
//		return newInstance(ModuleConstant.CommonCode.SUCCESS_CODE, ModuleConstant.CommonMessage.SUCCESS_MESSAGE);
//	}
//
//
//	//code=201  /** 用户没有登陆提示 */
//	public static Jsonp noLoginError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.NO_LOGIN_CODE, msg);
//	}
//	//code=202  /** 用户没有授权提示 */
//	public static Jsonp noAccreditError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.NO_ACCREDIT_CODE, msg);
//	}
//
//	// code=300
//	public static Jsonp paramError() {
//		return newInstance(ModuleConstant.CommonCode.PARAM_ERROR_CODE, ModuleConstant.CommonMessage.PARAM_ERROR_MESSAGE);
//	}
//
//	public static Jsonp paramError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.PARAM_ERROR_CODE, msg);
//	}
//
//	//code=400
//	public static Jsonp bussiness_tips_code(String msg) {
//		return newInstance(ModuleConstant.CommonCode.BUSINESS_TIPS_CODE, msg);
//	}
//
//	// code=500
//	public static Jsonp error() {
//		return newInstance(ModuleConstant.CommonCode.ERROR_CODE, ModuleConstant.CommonMessage.ERROR_MESSAGE);
//	}
//
//	public static Jsonp error(String msg) {
//		return newInstance(ModuleConstant.CommonCode.ERROR_CODE, msg);
//	}
//
//
//
//
//	public static Jsonp successNews() {
//		return newInstance(ModuleConstant.CommonCode.SUCCESS_CODE_NEWS, ModuleConstant.CommonMessage.SUCCESS_MESSAGE);
//	}
//
//
//
//	public static Jsonp error_md5() {
//		return newInstance(ModuleConstant.CommonCode.MD5_ERROR_CODE, ModuleConstant.CommonMessage.MD5_ERROR_MESSAGE);
//	}
//
//	/** 短时间内短信发送量超出限制提示 */
//	public static Jsonp smsNumberLimit() {
//		return newInstance(ModuleConstant.CommonCode.SMS_LIMIT_CODE, ModuleConstant.CommonMessage.SMS_LIMIT_MESSAGE);
//	}
//
//
//
//
//
//
//
//	/** 结算序列失效 */
//	public static Jsonp payOffFailedError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.PAY_OFF_FAILED, msg);
//	}
//
//	/** 系统最新的版本 */
//	public static Jsonp currentVersionState(String msg) {
//		return newInstance(ModuleConstant.CommonCode.CURRENT_VERSION, msg);
//	}
//
//	/** 查询信息失败 */
//	public static Jsonp searchFailureError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.SEARCH_FAILURE_CODE, msg);
//	}
//
//	/** 账号密码验证失败 */
//	public static Jsonp acountFailureError(String msg) {
//		return newInstance(ModuleConstant.CommonCode.ACOUNT_FAILURE_CODE, msg);
//	}
//
//	public static Jsonp newInstance(String code, String msg) {
//		return new Jsonp(code, msg);
//	}
//
//}
