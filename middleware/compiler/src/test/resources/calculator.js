{
	"name": "test",
	
	"resources": {
		"calculator": {
			"methods": {
				"sum": {					
					"httpMethod": "GET",
					"path": "calculator/sum",
					"parameters": {
						"x": {
							"type": "number",
							"required": true,
							"location": "query"							
						},
						"y": {
							"type": "number",
							"required": true,
							"location": "query"
						}						
					},
					"response": {
						"$ref": "double"
					}
				},
				
				"difference": {					
					"httpMethod": "GET",
					"path": "calculator/difference",
					"parameters": {
						"x": {
							"type": "number",
							"required": true,
							"location": "query"							
						},
						"y": {
							"type": "number",
							"required": true,
							"location": "query"
						}						
					},
					"response": {
						"$ref": "double"
					}
				},
				
				"product": {					
					"httpMethod": "GET",
					"path": "calculator/product",
					"parameters": {
						"x": {
							"type": "number",
							"required": true,
							"location": "query"							
						},
						"y": {
							"type": "number",
							"required": true,
							"location": "query"
						}						
					},
					"response": {
						"$ref": "double"
					}
				},
				
				"quotient": {
					"httpMethod": "GET",
					"path": "calculator/quotient",
					"parameters": {
						"x": {
							"type": "number",
							"required": true,
							"location": "query"							
						},
						"y": {
							"type": "number",
							"required": true,
							"location": "query"
						}						
					},
					"response": {
						"$ref": "double"
					}
				}
			}
		}
	}
}