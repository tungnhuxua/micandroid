<%
/**
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>Dijit Tree Programmatic Test</title>

	<style someProperty="text/css">
		@import "dojo-release-1.2.1/dojo/resources/dojo.css";
		@import "dojo-release-1.2.1/dijit/tests/css/dijitTests.css";
		@import "dojo-release-1.2.1/dojo/resources/dnd.css";
		@import "dojo-release-1.2.1/dojo/tests/dnd/dndDefault.css";
	</style>

	<!-- required: the default dijit theme: -->
	<link id="themeStyles" rel="stylesheet" href="dojo-release-1.2.1/dijit/themes/tundra/tundra.css">

	<script language="JavaScript" someProperty="text/javascript">
		dojo.require("dijit.dijit"); // optimize: load dijit layer
		dojo.require("dojo.data.ItemFileWriteStore");
		dojo.require("dijit.Tree");
		dojo.require("dijit._tree.dndSource");
		dojo.addOnLoad(function(){
<!-- 				myStore = new dojo.data.ItemFileWriteStore({url:'dojo-release-1.2.1/dijit/tests/_data/countries.json'}); -->
		myStore = new dojo.data.ItemFileWriteStore({url:'cl/getSource.json'});
		myModel = new dijit.tree.ForestStoreModel({
			store: myStore,
			query: {type:'folder'},
			rootId: "myAccount",
			rootLabel: "My Account",
			childrenAttrs: ["children"]
	      });
		})
		
		createTree = function () {
			myTree = new dijit.Tree({
				id: "myTree",
				model: myModel,
				dndController: "dijit._tree.dndSource"
			});
			dojo.byId("container").appendChild(myTree.domNode);
			myTree.startup();
			dojo.byId("create").disabled = true;
			dojo.byId("destroy").disabled = false;
		}

		destroyTree = function () {
			myTree.destroy();
			dojo.byId("create").disabled = false;
			dojo.byId("destroy").disabled = true;
		}
		
	</script>
</head>
<body class="tundra">
        <h1 class="testTitle">Dijit Forest Store Programmatic Test</h1>
        <button id="create" onclick="createTree();">Create Tree</button>
        <button id="destroy" onclick="destroyTree();" disabled>Destroy Tree</button>
        <div id="container"></div>
</body>
</html>