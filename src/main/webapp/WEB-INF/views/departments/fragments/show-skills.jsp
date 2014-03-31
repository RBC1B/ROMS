<%--
The contents of the department linked skills.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="entity-list-results">
    <table class="table table-bordered table-condensed table-striped table-hover" id="department-skills-list">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>On badge</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${skills}" var="skill">
                <tr>
                    <td><c:out value="${skill.name}" /></td>
                    <td><c:if test="${not empty skill.description}"><c:out value="${skill.description}" /></c:if></td>
                    <c:choose>
                        <c:when test="${not empty skill.category}">
                            <td>
                                <c:choose>
                                    <c:when test="${!empty skill.category.colour}">
                                        <c:set var="categoryColour" value="${fn:toLowerCase(skill.category.colour)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="categoryColour" value="white" />
                                    </c:otherwise>
                                </c:choose>
                                <div class="label label-default category-colour-${categoryColour}">
                                    <c:out value="${skill.category.name}" />
                                </div>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${skill.category.appearOnBadge}">
                                        <span class="glyphicon glyphicon-ok">
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td><span class="glyphicon glyphicon-remove"></span></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
