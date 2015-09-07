package com.aerifiu.backend;

import com.aerifiu.jokesource.JokeDispenser;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(
		name = "jokeApi",
		version = "v1",
		namespace = @ApiNamespace(
				ownerDomain = "backend.aerifiu.com",
				ownerName = "backend.aerifiu.com",
				packagePath = ""
		)
)

public class JokeEndpoint {

	@ApiMethod(name = "getJoke")
	public JokeBean getJoke() {
		JokeBean jokeBean = new JokeBean();
		jokeBean.setJoke(JokeDispenser.getJoke());
		return jokeBean;
	}
}
