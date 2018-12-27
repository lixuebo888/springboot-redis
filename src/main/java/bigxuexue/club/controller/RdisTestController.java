package bigxuexue.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bigxuexue.club.domain.JsonData;

@RestController
@RequestMapping("/api/v1/redis")
public class RdisTestController {

	@Autowired
	private StringRedisTemplate redisTpl; // jdbcTemplate

	@GetMapping(value = "add")
	public Object add() {

		// opsForValue : Returns the operations performed on simple values (or Strings
		// in Redis terminology).

		redisTpl.opsForValue().set("name", "xdclass2018");
		redisTpl.opsForHash().put("hashValue","map1","map1-1");
		redisTpl.opsForList().leftPush("list","a");  
		redisTpl.opsForList().leftPush("list","b");  
		redisTpl.opsForList().leftPush("list","c");  
		return JsonData.buildSuccess();

	}

	@GetMapping(value = "get")
	public Object get() {

		String value = redisTpl.opsForValue().get("name");
		redisTpl.opsForHash().get("hashValue", "map1");
		redisTpl.opsForList().range("book", 0, -1);
		redisTpl.opsForList().index("list",1);
		return JsonData.buildSuccess(value);

	}

}
