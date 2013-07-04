<?xml version="1.0" encoding="UTF-8" ?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>${groupId}</groupId>
	<artifactId>${artifactId}-client</artifactId>
	<version>${version}</version>

	<packaging>jar</packaging>
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <build>
    	<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>				
				<configuration>
					<source>1.7</source>
					<target>1.7</target>        			
    			</configuration>
			</plugin>    		    	
    	</plugins>
    </build>    
    
    <dependencies>
<!-- compile -->    
    	<dependency>
    		<groupId>afluentes</groupId>
    		<artifactId>afluentes-middleware-client-deps</artifactId>
    		<version>0.0.1-SNAPSHOT</version>
    		<scope>compile</scope>
    		<type>pom</type>
    	</dependency>
    	
    	<dependency>
    		<groupId>${groupId}</groupId>
    		<artifactId>${artifactId}-api</artifactId>
    		<version>${version}</version>
    		<scope>compile</scope>
    	</dependency>    	
<!-- compile -->    	    	
    </dependencies>	
</project>