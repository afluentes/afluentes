<?xml version="1.0" encoding="UTF-8" ?>

<web-app xmlns="http://java.sun.com/xml/ns/javaee"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
		 					 http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
		 metadata-complete="true"
		 version="3.0">
<!-- RESTEasy -->
	<listener>
      	<listener-class>org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap</listener-class>
   	</listener>   	

	<context-param>
   		<param-name>resteasy.servlet.mapping.prefix</param-name>
   		<param-value>/api</param-value>   	      	      	
	</context-param>
	
   	<servlet>
      	<servlet-name>HttpServlet30Dispatcher</servlet-name>
      	<servlet-class>org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher</servlet-class>      	
      	<load-on-startup>1</load-on-startup>
      	<async-supported>true</async-supported>   	      	
	</servlet>

	<servlet-mapping>
		<servlet-name>HttpServlet30Dispatcher</servlet-name>
		<url-pattern>/api/*</url-pattern>
   	</servlet-mapping>   	
<!-- RESTEasy -->


		 
<!-- Spring -->
   	<listener>
      	<listener-class>org.jboss.resteasy.plugins.spring.SpringContextLoaderListener</listener-class>      	
   	</listener>

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/applicationContext.xml
		</param-value>
	</context-param>
<!-- Spring -->
</web-app>