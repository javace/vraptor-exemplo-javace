<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastros de Membros da JavaCE</title>
	</head>
	<body>
		<form action="<c:url value="/membros/pesquisa" />" name="form"
			method="get">
			<label>ID:</label>
			<input type="text" id="id" name="id" value=""/>
			<input type="submit" value="Pesquisar" title="Pesquisar"/>
		</form>
		
		<table border="1" width="100%" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<td >
								ID
							</td>
							<td>
								Nome
							</td>
							<td width="100">
								Email
							</td>
							<td>
								Ações
							</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty membro}">
							<tr>
								<td colspan="5" style="text-align: center;">
									Nenhum membro encontrado
								</td>
							</tr>
						</c:if>
						<c:forEach var="m" items="${membros}" varStatus="s">
							<tr>
								<td>
									${m.id}
								</td>
								<td style="text-align: left;">
									${m.nome}
								</td>
								<td>
									${m.email}
								</td>
								<td>
									<a href="<c:url value='/alterar/membro/${m.id}'/>">alterar</a>
									---
									<a href="<c:url value='/deletar/membro/${m.id}'/>">excluir</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<a href="${ctx}/novo/membro">Inserir novo membro na JavaCE</a>
	</body>
</html>