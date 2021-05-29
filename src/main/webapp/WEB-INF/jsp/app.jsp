<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${app.name}</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/"  > << Back </a> 

	<table width="100%" cellpadding="5" border="1" >
		<tr><td width="10%" valign="top">ID</td><td width="90%" valign="top">${app.appID}</td></tr>
		<tr><td valign="top">Version</td><td valign="top">${app.appVersion}</td></tr>
		<tr><td valign="top">Name</td><td valign="top">${app.name}</td></tr>
		<tr><td valign="top">Category</td valign="top"><td>${app.category}</td></tr>
		<tr><td valign="top">Installed</td><td valign="top">${app.installed}</td></tr>
		<tr><td valign="top">Logo</td><td valign="top"><img src="${app.logo}" width="50" /></td></tr>
		<tr><td valign="top">Short Description</td><td valign="top">${app.shortDescription}</td></tr>
		<tr><td valign="top">Description</td><td valign="top">${app.description}</td></tr>
		
		<tr><td valign="top" height="15">  </td><td valign="top"> </td></tr>
		
		<tr><td valign="top">UserID</td><td valign="top">${app.userID}</td></tr>
		<tr><td valign="top">Company</td><td valign="top">${app.company}</td></tr>
		<tr><td valign="top">Email</td><td valign="top">${app.email}</td></tr>
		<tr><td valign="top">AppSupport</td><td valign="top">${app.appSupport}</td></tr>
		<tr><td valign="top">PublishDate</td><td valign="top">${app.publishDate}</td></tr>
		<tr><td valign="top">UpdateDate</td><td valign="top">${app.updateDate}</td></tr>
		<tr><td valign="top">AppSize</td><td valign="top">${app.appSize}</td></tr>
		
		<tr><td valign="top" height="15">  </td><td valign="top"> </td></tr>
		
		<tr><td valign="top">RatingAvg</td><td valign="top">${app.ratingAvg}</td></tr>
		<tr><td valign="top">RatingTotal</td><td valign="top">${app.ratingTotal}</td></tr>
		<tr><td valign="top">Rating1</td><td valign="top">${app.rating1}</td></tr>
		<tr><td valign="top">Rating2</td><td valign="top">${app.rating2}</td></tr>
		<tr><td valign="top">Rating3</td><td valign="top">${app.rating3}</td></tr>
		<tr><td valign="top">Rating4</td><td valign="top">${app.rating4}</td></tr>
		<tr><td valign="top">Rating5</td><td valign="top">${app.rating5}</td></tr>
		 
		
		<tr><td valign="top" height="15">  </td><td valign="top"> </td></tr>
		
		<tr><td valign="top" height="15" colspan="2">  
		<c:forEach var="screenshot" items="${app.screenShot}">
			<img src="${screenshot}" width="80"  height="80" />
		</c:forEach>
		
		</td> </tr>
		
		
		
		
		<tr><td valign="top" height="15">  </td><td valign="top"> </td></tr>
		<tr><td valign="top">ReviewTotal</td><td valign="top">${app.reviewTotal}</td></tr>
		
		
		<tr><td valign="top">Review </td><td valign="top">
		
		<c:forEach var="appReview" items="${app.appReview}">
		
			 ID: ${appReview.reviewID} <br>
			 Name: ${appReview.reviewName} <br>
			 Rating: ${appReview.reviewRating} <br>
			 Date: ${appReview.reviewDate} <br>
			 Version: ${appReview.reviewVersion} <br>
			 Message: ${appReview.reviewMessage}<br>
			----------------------------------------------------------------- 
			<br>  
	
		</c:forEach>
		
		</td></tr> 
 
	
		 
	</table>
</body>
</html>