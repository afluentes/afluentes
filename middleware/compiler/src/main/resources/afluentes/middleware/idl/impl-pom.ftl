<?xml version="1.0" encoding="UTF-8" ?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>${groupId}</groupId>
	<artifactId>${artifactId}-impl</artifactId>
	<version>${version}</version>

	<packaging>war</packaging>
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		
		<org.slf4j.version>1.5.8</org.slf4j.version>
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
			
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.1</version>
				<configuration>		
					<path>/</path>			
					<port>80</port>
				</configuration>			
			</plugin>			
    	</plugins>
    </build>    
    
    <dependencies>
<!-- compile -->    
    	<dependency>
    		<groupId>afluentes</groupId>
    		<artifactId>afluentes-middleware-impl-deps</artifactId>
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

<!-- runtime -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>			
			<version><#noparse>${org.slf4j.version}</#noparse></version>			
			<scope>runtime</scope>
		</dependency>
<!-- runtime -->    	    	
    </dependencies>	
</project>