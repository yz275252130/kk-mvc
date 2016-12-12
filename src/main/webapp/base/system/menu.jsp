<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

					<ul>
						<c:forEach items="${menuList}" var="resource">
							<sec:authorize url="${resource.resourceUrl}">
								<li><a
									href="${pageContext.request.contextPath}${resource.resourceUrl}${resource.code}"
									data-target="divload" data-div="baseContainer" class="waves-effect"><i
										class="${resource.icon}"></i><span>${resource.resourceName}
									</span></a></li>
							</sec:authorize>
						</c:forEach>
					</ul>