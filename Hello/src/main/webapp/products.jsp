<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<link rel ="stylesheet" href ="./resources/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> <!-- integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous" -->
<script>window.jQuery || document.write('<script src="./resources/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="./resources/vendor/popper.min.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<title>상품 목록</title>

<script type="text/javascript">
var ajax1; // XMLhttpRequest객체를 저장할 변수, 전역변수선언

function createAJAX()
{
	ajax1 = new XMLHttpRequest();
}

function getData()//json 요청
{		
	var div_name = "majorCtgList";
//		var ctg_id = document.forms[form_name].elements["txt_user_id"].value;
	
	createAJAX(); // createXHR() 메소드 호출
	
	var url = "./ctgInfo_major.jsp"; //요청 url 설정
	var reqparam = "s_ctg_id=1";
	
	ajax1.onreadystatechange = resGetData; // 다되면 실행할 함수 등록(호출 아님. 역호출)
	ajax1.open("Post", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
	ajax1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	ajax1.send(reqparam);
	
}

function resGetData()
{
	if(ajax1.readyState == 4)
	{
		if(ajax1.status == 200)
		{
			//alert("2");
			
			var result = ajax1.responseText;
// 				alert(result);
			
				var objRes = eval("("+result+")");
//				alert(objRes);
			
			var num = objRes.datas.length;
			var res = "";
			var resDiv = document.getElementById("majorCtgList");
			
			if(num<1)
			{
				res += "";
			}
			else
			{
				for(var i=0; i<num; i++)
				{
					var majorCtgId = objRes.datas[i].majorCtgId;
					var majorCtgName = objRes.datas[i].majorCtgName;
					
					res +="<button class='dropdown-item' type='button'";
					res +=" onclick='select_majorCtg(this.innerText)'>";
					res += majorCtgName+"</button>";
				}
			}
			
// 				alert(res);
			resDiv.innerHTML = res;
			getProducts();
			
		}
	}
}

function select_majorCtg(major){
// 		alert(major);
		document.getElementById('majorCtg').innerText = major;
		getData2(major);
// 		getProducts(major);
}

function getData2(majorCtgName)//json 요청
{		
	var majorCtgName;
// 	alert(majorCtgName);
	
	createAJAX(); // createXHR() 메소드 호출
	
	var url = "./ctgInfo_sub.jsp"; //요청 url 설정
	var reqparam = "majorCtgName="+majorCtgName;
	
	ajax1.onreadystatechange = resGetData2; // 다되면 실행할 함수 등록(호출 아님. 역호출)
	ajax1.open("Post", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
	ajax1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	ajax1.send(reqparam);
}

function resGetData2()
{
	if(ajax1.readyState == 4)
	{
		if(ajax1.status == 200)
		{
			//alert("2");
			
			var result = ajax1.responseText;
// 				alert(result);
			
				var objRes = eval("("+result+")");
//				alert(objRes);
			
			var num = objRes.datas.length;
			var res = "";
			var resDiv = document.getElementById("subCtgList");
			
			if(num<1)
			{
				res += "";
			}
			else
			{
				for(var i=0; i<num; i++)
				{
					var majorCtgName = objRes.datas[i].majorCtgName;
					var subCtgId = objRes.datas[i].subCtgId;
					var subCtgName = objRes.datas[i].subCtgName;
					var ctgName = majorCtgName+"/"+subCtgName;
					res +="<button class='dropdown-item' type='button'";
					res +=" onclick='select_Category(this.innerText); getProducts(\""+ctgName+"\");'>";
					res += subCtgName+"</button>";
				}
			}
			
//				alert(res);
			resDiv.innerHTML = res;
				
		}
	}
}

function select_Category(ctgName){
// 	alert(ctgName);
	document.getElementById('subCtg').innerText = ctgName;
}

function getProducts(ctgName)//json 요청
{	
// 	alert(ctgName);
	
	createAJAX(); // createXHR() 메소드 호출
	
	var url = "./getProducts.jsp"; //요청 url 설정
	var reqparam = "ctg_name="+ctgName;
	
	ajax1.onreadystatechange = resGetProducts; // 다되면 실행할 함수 등록(호출 아님. 역호출)
	ajax1.open("Post", url, "true"); //서버의 요청설정 -url변수에 설정된 리소스를 요청할 준비
	ajax1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	ajax1.send(reqparam);
}

function resGetProducts()
{
	if(ajax1.readyState == 4)
	{
		if(ajax1.status == 200)
		{
			//alert("2");
			
			var result = ajax1.responseText;
// 				alert(result);
			
				var objRes = eval("("+result+")");
//				alert(objRes);
			
			var num = objRes.datas.length;
			var res = "";
			var resDiv = document.getElementById("productsList");
			
			if(num<1)
			{
				res += "";
			}
			else
			{
				for(var i=0; i<num; i++)
				{
					var p_id = objRes.datas[i].p_id;
					var p_name = objRes.datas[i].p_name;
					var p_price = objRes.datas[i].p_price;
					var p_description = objRes.datas[i].p_description;
					var p_filename = objRes.datas[i].p_filename;
					var ctg_name = objRes.datas[i].ctg_name;
					
					res +="<div class='col-md-4'>"
					res +="<img src ='./upload2/"+p_filename+"' style ='width: 100%'>";
					res += "<h3>"+p_name+"</h3>";
					res += "<p>"+p_description+"</p>";
					res += "<p>"+p_price+"원</p>";
					res += "<a href='./product.jsp?id="+p_id+"' class='btn btn-secondary' role='button'> 상세 정보 &raquo;</a>"
					res += "</div>"
				}
			}
			
//				alert(res);
			resDiv.innerHTML = res;
			
		}
	}
}

</script>
</head>
<body onload="getData()">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품목록</h1>


		</div>
		<ul class="nav nav-tabs">
			<li class="nav-item dropdown">
				<button id="majorCtg" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">Category</button>
				<div id="majorCtgList" class="dropdown-menu">
				
				</div>&raquo;
			</li>
			<li class="nav-item dropdown">
				<button id="subCtg" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">All</button>
				<div id="subCtgList" class="dropdown-menu">
				</div>
			</li>
		</ul>
	</div>

	<div class="container">
		<div id="productsList" class="row" align="center">

		
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
