
<c:if test='${avatarPhotoUrl == null}'>
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${sessionScope.org.defaultMalePortrait}"/>) no-repeat scroll 0 0; list-style-type: none; width:75px; height:75px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
</c:if>
<c:if test='${avatarPhotoUrl != null}'>
<div style='position:relative;'>
<ul style='background: transparent url(<%= request.getContextPath() %><c:out value="${avatarPhotoUrl}"/>) no-repeat scroll 0 0; list-style-type: none; width:75px; height:75px;margin:0 0 0 10px;padding:0;-moz-border-radius:8px;'>
  <li>	
 </li>
</ul>
</div>
</c:if>