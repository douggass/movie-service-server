package com.movie;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.movie.module.ClientModule;
import com.movie.module.ControllerModule;
import com.movie.module.GeneralModule;
import com.movie.module.ServerModule;
import com.movie.module.ServiceModule;
import com.movie.server.Server;

public class Main {

	public static void main(String[] args) {

		final Injector injector = Guice.createInjector(
				new ClientModule(),
				new GeneralModule(),
				new ServiceModule(),
				new ControllerModule(),
				new ServerModule());

		injector.getInstance(Server.class).start();
	}
}
