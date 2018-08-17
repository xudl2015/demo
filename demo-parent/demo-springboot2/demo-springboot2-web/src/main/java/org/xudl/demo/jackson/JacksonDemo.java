package org.xudl.demo.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JacksonDemo {

	public static void main(String[] args) throws Exception {
		DemoVo demoVo = new DemoVo();
		demoVo.setName("xudl");
		demoVo.setAge(20);
		demoVo.setBirthDay(new Date());
		demoVo.setCompany("");
		demoVo.setLocalUserName("localName");
		demoVo.setPhone("13512345645");

		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		// mapper.setSerializationInclusion(Include.USE_DEFAULTS);
		/*mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString("");
			}
		});*/
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		SimpleBeanPropertyFilter fieldFilter = SimpleBeanPropertyFilter.serializeAllExcept("name");
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("cusFilter", fieldFilter);
		filterProvider.addFilter("phoneFilter", new SimpleBeanPropertyFilter() {
			@Override
			public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider,
			        PropertyWriter writer) throws Exception {
				if (pojo instanceof DemoVo) {
					DemoVo pojoVo = (DemoVo) pojo;
					pojoVo.setPhone("123xxxx333");
					super.serializeAsField(pojoVo, jgen, provider, writer);
				} else {
					super.serializeAsField(pojo, jgen, provider, writer);
				}
			}
		});
		mapper.setFilterProvider(filterProvider);

		String json = mapper.writeValueAsString(demoVo);
		System.out.println(json);
		json = json.replaceAll("123xxxx333", "13526548756");

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		DemoVo vo = mapper.readValue(json, DemoVo.class);
		System.out.println(vo.getLocalUserName());
		System.out.println(vo.getPhone());

		/*String mapJson = "{\"one\":{\"name\":\"张三\",\"age\":21,\"address\":\"深圳\"},"
		        + "\"two\":{\"name\":\"李四\",\"age\":11,\"address\":\"武汉\"},"
		        + "\"three\":{\"name\":\"王五\",\"age\":31,\"address\":\"北京\"}}";
		JavaType javaType2 = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, DemoVo.class);
		Map<String, DemoVo> userMap = (Map<String, DemoVo>) mapper.readValue(mapJson, javaType2);
		DemoVo one = userMap.get("one");
		DemoVo two = userMap.get("two");
		DemoVo three = userMap.get("three");
		
		System.out.println(one.getAddress() + " " + two.getAddress() + " " + three.getAddress());*/
	}
}
