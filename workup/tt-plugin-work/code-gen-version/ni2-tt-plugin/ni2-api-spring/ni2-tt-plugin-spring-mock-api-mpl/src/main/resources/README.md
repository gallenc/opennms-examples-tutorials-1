# changes to Ni2DeckAPIGTW-1.0.1-swagger-formatted.json

1. reformatted to formatted jason instead of 'all one line'. This is needed to be able to edit in Eclipse. Using https://jsoneditoronline.org/

2. added dummy Circuit as this was not in  the model and prevented generation

```
    "schemas": {
      "Circuit": {
        "type": "object",
        "properties": {
          "name": {
            "type": "string"
          }
        }
      },
      "CivicAddress": {
      
this is needed for      
                "schema": {
                  "anyOf": [
                    {
                      "$ref": "#/components/schemas/Described"
                    },
                    {
                      "$ref": "#/components/schemas/Circuit"
                    }
                  ]
                }
```