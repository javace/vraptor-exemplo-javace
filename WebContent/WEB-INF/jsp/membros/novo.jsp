<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Cadastrar Membro</title>
	</head>
	<body>		
			<span id="error" >
				<c:forEach var="error" items="${errors}"><li>${error.message}</li></c:forEach>
			</span>
			<span id="erros">${erros}</span>
			<span id="notice">${notice}</span>
		<form action="<c:url value="/novo/membro" />" name="form" method="post">
					<div>
						<label>
							Nome
						</label>						
						<input type="text" name="membro.nome" id="nome" size="50" maxlength="50"
							value="" />
					</div>
					<div>
						<label>
							Email
						</label>						
						<input type="text" name="membro.email" size="50" maxlength="50"
							value=""/>
					</div>
					<input type="submit" value="Salvar" name="Salvar" />
				</form>
	</body>
</html>