package com.ckfinder.connector.handlers.command;

import java.util.Collection;
import java.util.Locale;

import org.apache.struts2.util.RedisUtils;
import org.apache.struts2.util.SerializeUtil;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;

public class MyCaptchaStore implements CaptchaStore
{

	@Override
	public void cleanAndShutdown() {
		// TODO Auto-generated method stub
		System.out.println("cleanAndShutdown==>");
	}

	@Override
	public void empty() {
		// TODO Auto-generated method stub
		System.out.println("empty==>");
	}

	@Override
	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		// TODO Auto-generated method stub
		System.out.println("my getCaptcha---->");
		byte[] value = RedisUtils.getJedis().get(("jcap_"+id).getBytes());
		Object captchaAndLocale = (Object)SerializeUtil.unserialize(value);
		
		Captcha tmpcaptch = captchaAndLocale != null ? ((CaptchaAndLocale) captchaAndLocale)
				.getCaptcha() : null;
		return tmpcaptch;
	}

	@Override
	public Collection getKeys() {
		// TODO Auto-generated method stub
		System.out.println("getKeys==>");
		return RedisUtils.getJedis().keys("jcap_".getBytes());
	}

	@Override
	public Locale getLocale(String id) throws CaptchaServiceException {
		// TODO Auto-generated method stub
		System.out.println("getLocale==>");
		Object captchaAndLocale = this.getCaptcha(id);
        if (captchaAndLocale != null && captchaAndLocale instanceof CaptchaAndLocale) {
            return ((CaptchaAndLocale) captchaAndLocale).getLocale();
        }
		return null;
	}

	@Override
	public int getSize() {
		System.out.println("getSize==>");
		// TODO Auto-generated method stub
		//RedisUtils.getJedis().
//		return Math.toIntExact(RedisUtils.getJedis().);
		return 0;
	}

	@Override
	public boolean hasCaptcha(String id) {
		// TODO Auto-generated method stub
		System.out.println("my hasCaptcha---->"+id);
		boolean flg = RedisUtils.getJedis().exists(("jcap_"+id).getBytes());
//		if(flg)
//		{
//			byte[] value = RedisUtils.getJedis().get(("jcap_"+id).getBytes());
//			CaptchaAndLocale object = (CaptchaAndLocale)SerializeUtil.unserialize(value);
//			System.out.println(object.getCaptcha().hasGetChalengeBeenCalled().booleanValue());
//		}
//		
//		System.out.println("flg===>"+flg);
		return flg;
	}

	@Override
	public void initAndStart() {
		// TODO Auto-generated method stub
		System.out.println("initAndStart==>");
	}

	@Override
	public boolean removeCaptcha(String id) {
		// TODO Auto-generated method stub
		System.out.println("my removeCaptcha---->");
		if (this.hasCaptcha(id)) {
            RedisUtils.getJedis().del(("jcap_"+id).getBytes());
            return true;
        } else {
            return false;
        }
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha)
			throws CaptchaServiceException {
		// TODO Auto-generated method stub
		System.out.println("my strore---->");
		RedisUtils.getJedis().set(("jcap_"+id).getBytes(), SerializeUtil.serialize(new CaptchaAndLocale(captcha)));
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale locale)
			throws CaptchaServiceException {
		// TODO Auto-generated method stub
		System.out.println("my strore3---->");
		CaptchaAndLocale tmpobj = new CaptchaAndLocale(captcha, locale);
		tmpobj.getCaptcha().getChallenge();
		RedisUtils.getJedis().set(("jcap_"+id).getBytes(), SerializeUtil.serialize(tmpobj));
		
//		Jedis redis= new Jedis( "127.0.0.1", 6379);
//		redis.set(("jcap_"+id).getBytes(), SerializeUtil.serialize(new CaptchaAndLocale(captcha, locale)));
//		byte[] value = redis.get(("jcap_"+id).getBytes());
//		CaptchaAndLocale object = (CaptchaAndLocale)SerializeUtil.unserialize(value);
//		System.out.println(object.getCaptcha().getQuestion());
	}
}
