//添加音乐
function blogManagerAddMusic(name,uri){

	var url = "member-music!addMusic.action";
	var data = {'entity.musicName':name,'entity.musicUri':uri};
	jQuery.post(url,data,function(data){
		if("notTenRows"==data){
			alert("目前最多只支持引用十首歌曲，您已经超过十首歌曲！");
			return ;
		}else{
			$("#musicName").attr("value","");
			$("#musicUri").attr("value","");
			alert("添加音乐成功，您可以继续添加！");
		}
		
	});
}