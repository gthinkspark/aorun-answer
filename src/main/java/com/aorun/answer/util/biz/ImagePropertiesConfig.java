//package com.aorun.answer.util.biz;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//
///**
// *
// * @description
// * @author 杜习虎
// * @date 2015年11月2日下午3:04:54
// */
//public class ImagePropertiesConfig {
//	/** 读取配置文件 image.properties */
//	protected final static Logger log = LoggerFactory.getLogger(ImagePropertiesConfig.class);
//	static {
//
//		PropertyUtil props = new PropertyUtil(System.getenv("aorun_env_config_path") + "/account/image.properties");
//
//		CDN_SERVER_ROOT_PATH = props.getCommonConf("cdn.server_root.path");
//		//FILE_SERVER_ROOT_PATH = props.getCommonConf("file.server_root.path");
//		FILE_PATH = props.getCommonConf("file.path");
//	}
//
////	/** 服务器端的资源根路径 */
////	public static String FILE_SERVER_ROOT_PATH;
//	/** 服务器端的资源根路径 */
//	public static String CDN_SERVER_ROOT_PATH;
//	/** 后台上传文件的路径 */
//	public static String FILE_PATH;
//
//
//
//	/** 申请办卡-身份证照片 服务器存放位置 */
//	public static final String APPLY_CARD_PATH = FILE_PATH +  "worker" +  File.separator  + "applycard"+  File.separator ;
//	/** 申请办卡-身份证照片--web服务路径 */
//	public static final String APPLY_CARD_SERVER_PATH = CDN_SERVER_ROOT_PATH +  "worker"+  File.separator  + "applycard"+  File.separator ;
//
//
//	/** 工会卡-图片 服务器存放位置 */
//	public static final String CARD_PATH = FILE_PATH  + "worker" +  File.separator  + "card"+  File.separator ;
//	/** 工会卡-图片--web服务路径 */
//	public static final String CARD_SERVER_PATH = CDN_SERVER_ROOT_PATH + "worker"+  File.separator  + "card"+  File.separator ;
//
//
//
//	/** 商家推荐-图片 服务器存放位置 */
//	public static final String COMPANY_RECOMMEND_PATH = FILE_PATH  + "worker" +  File.separator  + "card"+  File.separator ;
//	/** 商家推荐-图片--web服务路径 */
//	public static final String COMPANY_RECOMMEND_SERVER_PATH = CDN_SERVER_ROOT_PATH  + "worker"+  File.separator  + "card"+  File.separator ;
//
//
//
//	/** 申请理赔-图片 服务器存放位置 */
//	public static final String APPLY_CLAIM_PATH = FILE_PATH + "worker" +  File.separator  + "applyclaim"+  File.separator ;
//	/** 申请理赔-图片--web服务路径 */
//	public static final String APPLY_CLAIM_SERVER_PATH = CDN_SERVER_ROOT_PATH  + "worker"+  File.separator  + "applyclaim"+  File.separator ;
//
//
//
//	/** 申请理赔-图片 服务器存放位置 */
//	public static final String ADVISORY_PATH = FILE_PATH  + "worker" +  File.separator  + "advisory"+  File.separator ;
//	/** 申请理赔-图片--web服务路径 */
//	public static final String ADVISORY_SERVER_PATH = CDN_SERVER_ROOT_PATH + "worker"+  File.separator  + "advisory"+  File.separator ;
//
//
//	/** 工会会员头像-图片--web服务路径 */
//	public static final String WORKERMEMBER_SERVER_PATH = CDN_SERVER_ROOT_PATH + "worker"+  File.separator  + "workermember"+  File.separator ;
//
//
//	/** 律师头像-图片--web服务路径 */
//	public static final String WORKERATTORNEY_SERVER_PATH = CDN_SERVER_ROOT_PATH + "worker"+  File.separator  + "workerattorney"+  File.separator ;
//
//
//
//}
