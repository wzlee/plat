//	HYPE.documents["index"]

(function HYPE_DocumentLoader() {
	var resourcesFolderName = "index.hyperesources";
	var documentName = "index";
	var documentLoaderFilename = "index_hype_generated_script.js";
	var mainContainerID = "index_hype_container";

	// find the URL for this script's absolute path and set as the resourceFolderName
	try {
		var scripts = document.getElementsByTagName('script');
		for(var i = 0; i < scripts.length; i++) {
			var scriptSrc = scripts[i].src;
			if(scriptSrc != null && scriptSrc.indexOf(documentLoaderFilename) != -1) {
				resourcesFolderName = scriptSrc.substr(0, scriptSrc.lastIndexOf("/"));
				break;
			}
		}
	} catch(err) {	}

	// Legacy support
	if (typeof window.HYPE_DocumentsToLoad == "undefined") {
		window.HYPE_DocumentsToLoad = new Array();
	}
 
	// load HYPE.js if it hasn't been loaded yet
	if(typeof HYPE_160 == "undefined") {
		if(typeof window.HYPE_160_DocumentsToLoad == "undefined") {
			window.HYPE_160_DocumentsToLoad = new Array();
			window.HYPE_160_DocumentsToLoad.push(HYPE_DocumentLoader);

			var headElement = document.getElementsByTagName('head')[0];
			var scriptElement = document.createElement('script');
			scriptElement.type= 'text/javascript';
			scriptElement.src = resourcesFolderName + '/' + 'HYPE.js?hype_version=160';
			headElement.appendChild(scriptElement);
		} else {
			window.HYPE_160_DocumentsToLoad.push(HYPE_DocumentLoader);
		}
		return;
	}
	
	// handle attempting to load multiple times
	if(HYPE.documents[documentName] != null) {
		var index = 1;
		var originalDocumentName = documentName;
		do {
			documentName = "" + originalDocumentName + "-" + (index++);
		} while(HYPE.documents[documentName] != null);
		
		var allDivs = document.getElementsByTagName("div");
		var foundEligibleContainer = false;
		for(var i = 0; i < allDivs.length; i++) {
			if(allDivs[i].id == mainContainerID && allDivs[i].getAttribute("HYPE_documentName") == null) {
				var index = 1;
				var originalMainContainerID = mainContainerID;
				do {
					mainContainerID = "" + originalMainContainerID + "-" + (index++);
				} while(document.getElementById(mainContainerID) != null);
				
				allDivs[i].id = mainContainerID;
				foundEligibleContainer = true;
				break;
			}
		}
		
		if(foundEligibleContainer == false) {
			return;
		}
	}
	
	var hypeDoc = new HYPE_160();
	
	var attributeTransformerMapping = {b:"i",c:"i",bC:"i",d:"i",aS:"i",M:"i",e:"f",aT:"i",N:"i",f:"d",O:"i",g:"c",aU:"i",P:"i",Q:"i",aV:"i",R:"c",bG:"f",aW:"f",aI:"i",S:"i",bH:"d",l:"d",aX:"i",T:"i",m:"c",bI:"f",aJ:"i",n:"c",aK:"i",bJ:"f",X:"i",aL:"i",A:"c",aZ:"i",Y:"bM",B:"c",bK:"f",bL:"f",C:"c",D:"c",t:"i",E:"i",G:"c",bA:"c",a:"i",bB:"i"};
	
	var resources = {"10":{n:"dot.p.png",p:1},"2":{n:"a1.png",p:1},"3":{n:"a3.png",p:1},"11":{n:"dot.png",p:1},"4":{n:"nav-slide222.png",p:1},"5":{n:"Pasted.png",p:1},"12":{n:"product-list-1.png",p:1},"6":{n:"Pasted-1.png",p:1},"13":{n:"iTem.png",p:1},"7":{n:"b1.png",p:1},"0":{n:"product-list.png",p:1},"8":{n:"b2.png",p:1},"1":{n:"product-mall-jd-fix.png",p:1},"9":{n:"b3.png",p:1}};
	
	var scenes = [{x:0,p:"600px",c:"#FFFFFF",v:{"84":{o:"content-box",h:"11",x:"visible",a:1112,q:"100% 100%",b:346,j:"absolute",r:"inline",c:16,k:"div",z:"8",d:16},"21":{o:"content-box",h:"4",x:"visible",a:230,q:"100% 100%",b:203,j:"absolute",r:"inline",c:962,k:"div",z:"10",d:700,e:"0.000000"},"74":{o:"content-box",h:"6",x:"visible",a:296,q:"100% 100%",b:157,j:"absolute",r:"inline",c:91,k:"div",z:"30",d:97,e:"0.000000"},"3":{o:"content-box",h:"1",p:"repeat",x:"visible",a:0,b:0,j:"absolute",r:"inline",c:1641,k:"div",z:"1",d:2465},"26":{c:727,d:698,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",aA:[{type:1,transition:1,sceneOid:"8"}],O:1,B:"#A0A0A0",z:"13",P:1,D:"#A0A0A0",aC:[{timelineIdentifier:"24",type:3}],C:"#A0A0A0",a:463,aD:[{timelineIdentifier:"24",type:7}],b:203},"79":{o:"content-box",h:"8",x:"visible",a:474,q:"100% 100%",b:171,j:"absolute",r:"inline",c:717,k:"div",z:"4",d:200,e:"1.000000"},"22":{c:231,d:38,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",O:1,P:1,z:"12",C:"#A0A0A0",D:"#A0A0A0",aC:[{timelineIdentifier:"24",type:3}],a:230,aD:[{timelineIdentifier:"23",type:3}],b:203},"60":{c:181,d:72,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,z:"29",P:1,D:"#A0A0A0",C:"#A0A0A0",a:216,b:39},"27":{c:106,d:29,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"6"}],O:1,z:"14",P:1,D:"#A0A0A0",C:"#A0A0A0",a:479,aD:[{timelineIdentifier:"24",type:7}],b:212},"5":{o:"content-box",h:"0",p:"repeat",x:"visible",a:17,b:-1,j:"absolute",r:"inline",c:1375,k:"div",z:"2",d:1528,e:"0.000000"},"69":{o:"content-box",h:"5",x:"visible",a:207,q:"100% 100%",b:-1,j:"absolute",r:"inline",c:303,k:"div",z:"11",d:158},"75":{c:89,d:33,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",O:1,P:1,z:"31",C:"#A0A0A0",D:"#A0A0A0",aC:[{timelineIdentifier:"77",type:3}],a:296,aD:[{timelineIdentifier:"76",type:3}],b:122},"80":{o:"content-box",h:"9",x:"visible",a:474,q:"100% 100%",aW:"0.000000",j:"absolute",r:"inline",aX:0,k:"div",c:717,d:200,z:"5",b:171,e:"1.000000"},"34":{c:106,d:29,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"6"}],O:1,z:"21",P:1,D:"#A0A0A0",C:"#A0A0A0",a:705,aD:[{timelineIdentifier:"24",type:7}],b:489},"81":{S:0,bJ:"0.000000",c:16,q:"100% 100%",d:16,r:"inline",bK:"1.000000",bL:"0.000000",aW:"0.000000",aX:15.84,h:"10",j:"absolute",x:"visible",k:"div",bG:"0.000000",Q:0,z:"9",bH:"0deg",R:"#000000",bI:"1.000000",a:1112,o:"content-box",T:0,b:346},"82":{o:"content-box",h:"11",x:"visible",a:1136,q:"100% 100%",b:346,j:"absolute",r:"inline",c:16,k:"div",z:"6",d:16},"83":{o:"content-box",h:"11",x:"visible",a:1160,q:"100% 100%",b:346,j:"absolute",r:"inline",c:16,k:"div",z:"7",d:16},"29":{c:106,d:29,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"6"}],O:1,z:"16",P:1,D:"#A0A0A0",C:"#A0A0A0",a:953,aD:[{timelineIdentifier:"24",type:7}],b:212},"78":{o:"content-box",h:"7",x:"visible",a:474,q:"100% 100%",b:171,j:"absolute",r:"inline",c:717,k:"div",z:"3",d:200}},n:"Untitled Scene",T:{"76":{d:0.08,i:"76",n:"mall come",a:[{f:"2",t:0,d:0.08,i:"e",e:"1.000000",s:"-1.000000",o:"74"}],f:30},"77":{d:0.08,i:"77",n:"mall go",a:[{f:"2",t:0,d:0.08,i:"e",e:"-1.000000",s:"1.000000",o:"74"}],f:30},"4":{d:0,i:"4",n:"list",a:[],f:30},kTimelineDefaultIdentifier:{d:6.28,i:"kTimelineDefaultIdentifier",n:"Main Timeline",a:[{f:"2",t:2.08,d:0.05,i:"e",e:"0.000000",s:"1.000000",o:"80"},{f:"2",t:2.09,d:0.05,i:"a",e:1136,s:1112,o:"81"},{f:"2",t:2.13,d:4.1,i:"e",e:"0.000000",s:"0.000000",o:"80"},{f:"2",t:2.14,d:2,i:"a",e:1136,s:1136,o:"81"},{f:"2",t:4.13,d:0.04,i:"e",e:"0.000000",s:"1.000000",o:"79"},{f:"2",t:4.14,d:0.04,i:"a",e:1160,s:1136,o:"81"},{f:"2",t:4.17,d:0.09,i:"e",e:"0.000000",s:"0.000000",o:"79"},{f:"2",t:4.26,d:0.05,i:"e",e:"0.000000",s:"0.000000",o:"79"},{f:"2",t:6.23,d:0.04,i:"e",e:"1.000000",s:"0.000000",o:"80"},{f:"2",t:6.28,p:2,d:0,i:"Actions",s:[{timelineIdentifier:"kTimelineDefaultIdentifier",type:3}],o:"kTimelineDefaultIdentifier"}],f:30},"23":{d:0.13,i:"23",n:"list show",a:[{f:"2",t:0,d:0.13,i:"e",e:"1.000000",s:"0.000000",o:"21"}],f:30},"24":{d:0.1,i:"24",n:"list fin",a:[{f:"2",t:0,d:0.1,i:"e",e:"0.000000",s:"1.000000",o:"21"}],f:30}},o:"1"},{x:1,p:"600px",c:"#FFFFFF",v:{"64":{c:727,d:698,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",aA:[{type:1,transition:1,sceneOid:"8"}],O:1,B:"#A0A0A0",z:"7",P:1,D:"#A0A0A0",aC:[{timelineIdentifier:"67",type:3}],C:"#A0A0A0",a:463,aD:[{timelineIdentifier:"67",type:7}],b:203},"85":{o:"content-box",h:"12",x:"visible",a:17,q:"100% 100%",b:-1,j:"absolute",r:"inline",c:1440,k:"div",z:"2",d:1600,e:"1.000000"},"44":{c:174,d:126,I:"Solid",r:"inline",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,B:"#A0A0A0",z:"5",P:1,D:"#A0A0A0",C:"#A0A0A0",a:219,b:27}},n:"Untitled Scene 2",T:{"67":{d:0,i:"67",n:"list fin",a:[],f:30},"66":{d:0,i:"66",n:"list turn",a:[],f:30},kTimelineDefaultIdentifier:{d:0.16,i:"kTimelineDefaultIdentifier",n:"Main Timeline",a:[{f:"2",t:0,d:0.16,i:"e",e:"0.010000",s:"0.000000",o:"64"}],f:30}},o:"6"},{x:2,p:"600px",c:"#FFFFFF",v:{"13":{o:"content-box",h:"2",x:"visible",a:500,q:"100% 100%",b:305,j:"absolute",r:"inline",c:479,k:"div",z:"7",d:376,e:"0.000000"},"86":{o:"content-box",h:"13",x:"visible",a:15,q:"100% 100%",b:0,j:"absolute",r:"inline",c:1440,k:"div",z:"3",d:1478,e:"1.000000"},"52":{c:217,d:45,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"18"}],O:1,z:"8",P:1,D:"#A0A0A0",C:"#A0A0A0",a:669,b:593},"53":{c:38,d:36,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"8"}],O:1,z:"9",P:1,D:"#A0A0A0",C:"#A0A0A0",a:930,b:312},"48":{c:174,d:126,I:"Solid",r:"inline",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,B:"#A0A0A0",z:"5",P:1,D:"#A0A0A0",C:"#A0A0A0",a:219,b:27},"61":{c:174,d:126,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,z:"11",P:1,D:"#A0A0A0",C:"#A0A0A0",a:219,b:27},"20":{c:1485,d:1104,I:"Solid",r:"inline",e:"0.000000",J:"Solid",t:14,K:"Solid",g:"#000000",L:"Solid",M:1,w:"",N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",O:1,B:"#A0A0A0",P:1,z:"6",C:"#A0A0A0",D:"#A0A0A0",a:-52,b:0},"57":{c:187,d:53,I:"None",e:"0.000000",J:"None",t:18,K:"None",g:"#FFFFFF",L:"None",aP:"pointer",M:0,w:"",N:0,A:"#A0A0A0",j:"absolute",aA:[{timelineIdentifier:"50",type:3}],k:"div",O:0,x:"visible",B:"#A0A0A0",z:"10",P:0,D:"#A0A0A0",aC:[{timelineIdentifier:"59",type:3}],C:"#A0A0A0",a:541,aD:[{timelineIdentifier:"58",type:3}],b:440}},n:"Untitled Scene 3",T:{"51":{d:0,i:"51",n:"box go",a:[],f:30},"58":{d:0.09,i:"58",n:"btn go",a:[{f:"2",t:0,d:0.09,i:"e",e:"0.163462",s:"0.000000",o:"57"}],f:30},"50":{d:0.07,i:"50",n:"box come",a:[{f:"2",t:0,d:0.07,i:"e",e:"0.421547",s:"0.000000",o:"20"},{f:"2",t:0,d:0.07,i:"e",e:"1.000000",s:"0.000000",o:"13"}],f:30},"12":{d:0,i:"12",n:"box",a:[],f:30},kTimelineDefaultIdentifier:{d:0,i:"kTimelineDefaultIdentifier",n:"Main Timeline",a:[],f:30},"59":{d:0.07,i:"59",n:"btn out",a:[{f:"2",t:0,d:0.07,i:"e",e:"0.000000",s:"0.165210",o:"57"}],f:30}},o:"8"},{x:3,p:"600px",c:"#FFFFFF",v:{"49":{c:174,d:126,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,z:"5",P:1,D:"#A0A0A0",C:"#A0A0A0",a:219,b:27},"55":{c:38,d:36,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"8"}],O:1,z:"10",P:1,D:"#A0A0A0",C:"#A0A0A0",a:910,b:383},"19":{c:1485,d:1097,I:"Solid",r:"inline",e:"0.432474",J:"Solid",t:14,K:"Solid",g:"#000000",L:"Solid",M:1,w:"",N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",O:1,B:"#A0A0A0",P:1,z:"6",C:"#A0A0A0",D:"#A0A0A0",a:-51,b:-37},"87":{o:"content-box",h:"13",x:"visible",a:15,q:"100% 100%",b:0,j:"absolute",r:"inline",c:1440,k:"div",z:"2",d:1478,e:"1.000000"},"54":{c:159,d:45,I:"Solid",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",B:"#A0A0A0",k:"div",aA:[{type:1,transition:1,sceneOid:"8"}],O:1,z:"9",P:1,D:"#A0A0A0",C:"#A0A0A0",a:648,b:540},"62":{c:174,d:126,I:"Solid",r:"inline",e:"0.000000",J:"Solid",K:"Solid",g:"#DDEEFF",L:"Solid",aP:"pointer",M:1,N:1,A:"#A0A0A0",x:"visible",j:"absolute",k:"div",aA:[{type:1,transition:1,sceneOid:"1"}],O:1,B:"#A0A0A0",z:"11",P:1,D:"#A0A0A0",C:"#A0A0A0",a:219,b:27},"15":{o:"content-box",h:"3",x:"visible",a:478,q:"100% 100%",b:383,j:"absolute",r:"inline",c:479,k:"div",z:"8",d:241}},n:"Untitled Scene 3 Copy",T:{"12":{d:0,i:"12",n:"box",a:[],f:30},kTimelineDefaultIdentifier:{d:0,i:"kTimelineDefaultIdentifier",n:"Main Timeline",a:[],f:30}},o:"18"}];
	
	var javascripts = [];
	
	var functions = {};
	var javascriptMapping = {};
	for(var i = 0; i < javascripts.length; i++) {
		try {
			javascriptMapping[javascripts[i].identifier] = javascripts[i].name;
			eval("functions." + javascripts[i].name + " = " + javascripts[i].source);
		} catch (e) {
			hypeDoc.log(e);
			functions[javascripts[i].name] = (function () {});
		}
	}
	
	hypeDoc.setAttributeTransformerMapping(attributeTransformerMapping);
	hypeDoc.setResources(resources);
	hypeDoc.setScenes(scenes);
	hypeDoc.setJavascriptMapping(javascriptMapping);
	hypeDoc.functions = functions;
	hypeDoc.setCurrentSceneIndex(0);
	hypeDoc.setMainContentContainerID(mainContainerID);
	hypeDoc.setResourcesFolderName(resourcesFolderName);
	hypeDoc.setShowHypeBuiltWatermark(0);
	hypeDoc.setShowLoadingPage(false);
	hypeDoc.setDrawSceneBackgrounds(true);
	hypeDoc.setGraphicsAcceleration(true);
	hypeDoc.setDocumentName(documentName);

	HYPE.documents[documentName] = hypeDoc.API;
	document.getElementById(mainContainerID).setAttribute("HYPE_documentName", documentName);

	hypeDoc.documentLoad(this.body);
}());

