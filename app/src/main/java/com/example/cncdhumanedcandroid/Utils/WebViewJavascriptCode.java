package com.example.cncdhumanedcandroid.Utils;

public class WebViewJavascriptCode {

    public String provideJavascriptCode(String formjson, String function){

        String sumbitmethod = "\n" +
                "survey.onComplete.add((sender, options) => {" +
                "\n" +
                "Android.ShowProgressDialog()\n"+
                "        console.log(JSON.stringify(sender.data, null, 3));\n" +
                "        let refinedData = {};" +
                "\n" +
                "        let images_url = [];\n" +
                "        for (const key in sender.data) {\n" +
                "          if (Array.isArray(sender.data[key])) {\n" +
                "            sender.data[key].map((field, fk) => {\n" +
                "if(field.type != undefined){"+
                "              if((field.type).indexOf(\"image/\" === 0) ) {\n" +
                "                field.content = '';\n" +
                "                images_url.push({\n" +
                "                  image_name: key,\n" +
                "                  image_path: field.name\n" +
                "                })};\n" +
                "              }\n" +
                "            });\n" +
                "          }\n" +
                "        }\n" +
                "setTimeout(function(){\n" +
                "        console.log(sender.data);\n" +
                "        console.log(images_url[0]);\n" +

                "var myJsonString = JSON.stringify(images_url);\n"+
                " const results = JSON.stringify(sender.data);" +"\n"+
                function+"\n},2000)\n"+
                "      });";

        String javascriptCode =
                "      window['surveyjs-widgets'].inputmask(Survey);\n" +
                        "      window['surveyjs-widgets'].nouislider(Survey);\n" +
                        "const themeJson = {\n" +
                        "  \"cssVariables\": {\n" +
                        "    \"--sjs-general-backcolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dark\": \"rgba(248, 248, 248, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-light\": \"rgba(249, 249, 249, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-dark\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-general-dim-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-dim-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-primary-backcolor\": \"#2772cb\",\n" +
                        "    \"--sjs-primary-backcolor-light\": \"rgba(NaN, NaN, NaN, 0.07)\",\n" +
                        "    \"--sjs-primary-backcolor-dark\": \"rgba(NaN, NaN, NaN, 1)\",\n" +
                        "    \"--sjs-primary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-primary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-base-unit\": \"8px\",\n" +
                        "    \"--sjs-corner-radius\": \"4px\",\n" +
                        "    \"--sjs-secondary-backcolor\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-secondary-backcolor-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-secondary-backcolor-semi-light\": \"rgba(255, 152, 20, 0.25)\",\n" +
                        "    \"--sjs-secondary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-secondary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-shadow-small\": \"0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-shadow-medium\": \"0px 2px 6px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-large\": \"0px 8px 16px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-inner\": \"inset 0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-border-light\": \"rgba(0, 0, 0, 0.09)\",\n" +
                        "    \"--sjs-border-default\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-border-inside\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-special-red\": \"rgba(229, 10, 62, 1)\",\n" +
                        "    \"--sjs-special-red-light\": \"rgba(229, 10, 62, 0.1)\",\n" +
                        "    \"--sjs-special-red-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-green\": \"rgba(25, 179, 148, 1)\",\n" +
                        "    \"--sjs-special-green-light\": \"rgba(25, 179, 148, 0.1)\",\n" +
                        "    \"--sjs-special-green-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-blue\": \"rgba(67, 127, 217, 1)\",\n" +
                        "    \"--sjs-special-blue-light\": \"rgba(67, 127, 217, 0.1)\",\n" +
                        "    \"--sjs-special-blue-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-yellow\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-special-yellow-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-special-yellow-forecolor\": \"rgba(255, 255, 255, 1)\"\n" +
                        "  },\n" +
                        "  \"themeName\": \"default\",\n" +
                        "  \"colorPalette\": \"light\"\n" +
                        "}" +
                        "\n" +
                        "  const survey = new Survey.Model(" + formjson + ");\n" +
                        "\n" +
                        "  // You can delete the line below if you do not use a customized theme\n" +
                        "  survey.applyTheme(themeJson);\n" +
                        sumbitmethod+
                        "\n" +
                        "\n" +
                        "  $(\"#surveyElement\").Survey({ model: survey });";



        return javascriptCode;

    }


    public String ProvideJavascriptEditableCode(String formjson, String function, String formData){

                String sumbitmethod = "\n" +
                "survey.onComplete.add((sender, options) => {" +
                "\n" +
                "Android.ShowProgressDialog()\n"+
                "        console.log(JSON.stringify(sender.data, null, 3));\n" +
                "        let refinedData = {};" +
                "\n" +
                "        let images_url = [];\n" +
                "        for (const key in sender.data) {\n" +
                "          if (Array.isArray(sender.data[key])) {\n" +
                "            sender.data[key].map((field, fk) => {\n" +
                "if(field.type != undefined){"+
                "              if((field.type).indexOf(\"image/\" === 0) ) {\n" +
                "                field.content = '';\n" +
                "                images_url.push({\n" +
                "                  image_name: key,\n" +
                "                  image_path: field.name\n" +
                "                })};\n" +
                "              }\n" +
                "            });\n" +
                "          }\n" +
                "        }\n" +
                "setTimeout(function(){\n" +
                "        console.log(sender.data);\n" +
                "        console.log(images_url[0]);\n" +

                "var myJsonString = JSON.stringify(images_url);\n"+
                " const results = JSON.stringify(sender.data);" +"\n"+
                function+"\n},2000)\n"+
                "      });";
        String javascriptCode =
                "      window['surveyjs-widgets'].inputmask(Survey);\n" +
                        "      window['surveyjs-widgets'].nouislider(Survey);\n" +
                        "const themeJson = {\n" +
                        "  \"cssVariables\": {\n" +
                        "    \"--sjs-general-backcolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dark\": \"rgba(248, 248, 248, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-light\": \"rgba(249, 249, 249, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-dark\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-general-dim-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-dim-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-primary-backcolor\": \"#2772cb\",\n" +
                        "    \"--sjs-primary-backcolor-light\": \"rgba(NaN, NaN, NaN, 0.07)\",\n" +
                        "    \"--sjs-primary-backcolor-dark\": \"rgba(NaN, NaN, NaN, 1)\",\n" +
                        "    \"--sjs-primary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-primary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-base-unit\": \"8px\",\n" +
                        "    \"--sjs-corner-radius\": \"4px\",\n" +
                        "    \"--sjs-secondary-backcolor\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-secondary-backcolor-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-secondary-backcolor-semi-light\": \"rgba(255, 152, 20, 0.25)\",\n" +
                        "    \"--sjs-secondary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-secondary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-shadow-small\": \"0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-shadow-medium\": \"0px 2px 6px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-large\": \"0px 8px 16px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-inner\": \"inset 0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-border-light\": \"rgba(0, 0, 0, 0.09)\",\n" +
                        "    \"--sjs-border-default\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-border-inside\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-special-red\": \"rgba(229, 10, 62, 1)\",\n" +
                        "    \"--sjs-special-red-light\": \"rgba(229, 10, 62, 0.1)\",\n" +
                        "    \"--sjs-special-red-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-green\": \"rgba(25, 179, 148, 1)\",\n" +
                        "    \"--sjs-special-green-light\": \"rgba(25, 179, 148, 0.1)\",\n" +
                        "    \"--sjs-special-green-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-blue\": \"rgba(67, 127, 217, 1)\",\n" +
                        "    \"--sjs-special-blue-light\": \"rgba(67, 127, 217, 0.1)\",\n" +
                        "    \"--sjs-special-blue-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-yellow\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-special-yellow-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-special-yellow-forecolor\": \"rgba(255, 255, 255, 1)\"\n" +
                        "  },\n" +
                        "  \"themeName\": \"default\",\n" +
                        "  \"colorPalette\": \"light\"\n" +
                        "}" +
                        "\n" +
                        "  const survey = new Survey.Model(" + formjson + ");\n" +
                        "\n" +
                        "survey.data = "+ formData+";\n"+
                        "Android.dissmissdialog()\n"+
                        "  // You can delete the line below if you do not use a customized theme\n" +
                        "  survey.applyTheme(themeJson);\n" +
                        sumbitmethod+
                        "\n" +
                        "\n" +

                        "  $(\"#surveyElement\").Survey({ model: survey });";


        return javascriptCode;

    }


    public String provideJavaScriptCodeReadOnly(String formjson, String formData){

        String javascriptCode =
                "      window['surveyjs-widgets'].inputmask(Survey);\n" +
                        "      window['surveyjs-widgets'].nouislider(Survey);\n" +
                        "const themeJson = {\n" +
                        "  \"cssVariables\": {\n" +
                        "    \"--sjs-general-backcolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dark\": \"rgba(248, 248, 248, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-light\": \"rgba(249, 249, 249, 1)\",\n" +
                        "    \"--sjs-general-backcolor-dim-dark\": \"rgba(243, 243, 243, 1)\",\n" +
                        "    \"--sjs-general-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-general-dim-forecolor\": \"rgba(0, 0, 0, 0.91)\",\n" +
                        "    \"--sjs-general-dim-forecolor-light\": \"rgba(0, 0, 0, 0.45)\",\n" +
                        "    \"--sjs-primary-backcolor\": \"#2772cb\",\n" +
                        "    \"--sjs-primary-backcolor-light\": \"rgba(NaN, NaN, NaN, 0.07)\",\n" +
                        "    \"--sjs-primary-backcolor-dark\": \"rgba(NaN, NaN, NaN, 1)\",\n" +
                        "    \"--sjs-primary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-primary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-base-unit\": \"8px\",\n" +
                        "    \"--sjs-corner-radius\": \"4px\",\n" +
                        "    \"--sjs-secondary-backcolor\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-secondary-backcolor-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-secondary-backcolor-semi-light\": \"rgba(255, 152, 20, 0.25)\",\n" +
                        "    \"--sjs-secondary-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-secondary-forecolor-light\": \"rgba(255, 255, 255, 0.25)\",\n" +
                        "    \"--sjs-shadow-small\": \"0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-shadow-medium\": \"0px 2px 6px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-large\": \"0px 8px 16px 0px rgba(0, 0, 0, 0.1)\",\n" +
                        "    \"--sjs-shadow-inner\": \"inset 0px 1px 2px 0px rgba(0, 0, 0, 0.15)\",\n" +
                        "    \"--sjs-border-light\": \"rgba(0, 0, 0, 0.09)\",\n" +
                        "    \"--sjs-border-default\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-border-inside\": \"rgba(0, 0, 0, 0.16)\",\n" +
                        "    \"--sjs-special-red\": \"rgba(229, 10, 62, 1)\",\n" +
                        "    \"--sjs-special-red-light\": \"rgba(229, 10, 62, 0.1)\",\n" +
                        "    \"--sjs-special-red-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-green\": \"rgba(25, 179, 148, 1)\",\n" +
                        "    \"--sjs-special-green-light\": \"rgba(25, 179, 148, 0.1)\",\n" +
                        "    \"--sjs-special-green-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-blue\": \"rgba(67, 127, 217, 1)\",\n" +
                        "    \"--sjs-special-blue-light\": \"rgba(67, 127, 217, 0.1)\",\n" +
                        "    \"--sjs-special-blue-forecolor\": \"rgba(255, 255, 255, 1)\",\n" +
                        "    \"--sjs-special-yellow\": \"rgba(255, 152, 20, 1)\",\n" +
                        "    \"--sjs-special-yellow-light\": \"rgba(255, 152, 20, 0.1)\",\n" +
                        "    \"--sjs-special-yellow-forecolor\": \"rgba(255, 255, 255, 1)\"\n" +
                        "  },\n" +
                        "  \"themeName\": \"default\",\n" +
                        "  \"colorPalette\": \"light\"\n" +
                        "}" +
                        "\n" +
                        "  const survey = new Survey.Model(" + formjson + ");\n" +
                        "\n" +
                        "survey.data = "+ formData+";\n"+
                        "Android.dissmissdialog()\n"+
                        "  // You can delete the line below if you do not use a customized theme\n" +
                        "  survey.applyTheme(themeJson);\n" +
                        "\n" +
                        "\n" +
                        "  $(\"#surveyElement\").Survey({ model: survey });";

        return javascriptCode;

    }
}
