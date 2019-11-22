//package com.aorun.answer.util.biz;
//
//
//import com.aorun.answer.dto.UserDto;
//import com.aorun.answer.dto.WorkerMember;
//import com.aorun.answer.util.cache.redis.RedisCache;
//
///**
// * @author 作者 duxihu
// */
//public class WorkerMemberUtil {
//
//	public static Long getWorkerId(a
//			String sid){
//		UserDto user = (UserDto) RedisCache.get(sid);
//		WorkerMember workerMember = RedisCache.getStrObj(UnionUtil.generateUnionSid(user),WorkerMember.class);
//		return workerMember.getId();
//	}
//}
