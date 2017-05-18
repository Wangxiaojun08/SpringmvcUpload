package com.jun.converter.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.jun.converter.entity.Attachment;

public class StringArrayToAttachmentList implements Converter<String[], List<Attachment>> {

	@Override
	public List<Attachment> convert(String[] source) {
		if (source == null)
			return null;
		List<Attachment> attachs = new ArrayList<Attachment>(source.length);
		Attachment attach = null;
		for (String attachStr : source) {
			// 这里假设我们的Attachment是以“name,requestUrl,size”的形式拼接的。
			String[] attachInfos = attachStr.split(",");
			if (attachInfos.length != 3)// 当按逗号分隔的数组长度不为3时就抛一个异常，说明非法操作了。
				throw new RuntimeException();
			String name = attachInfos[0];
			String requestUrl = attachInfos[1];
			int size;
			try {
				size = Integer.parseInt(attachInfos[2]);
			} catch (NumberFormatException e) {
				throw new RuntimeException();// 这里也要抛一个异常。
			}
			attach = new Attachment(name, requestUrl, size);
			attachs.add(attach);
		}
		return attachs;
	}

}
