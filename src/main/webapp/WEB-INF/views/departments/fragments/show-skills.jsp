<%--
The contents of the department linked skills.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="entity-list-results">
    <table class="table table-bordered table-condensed table-striped table-hover" id="department-skills-list">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>On Badge</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${skills}" var="skill">
                <tr>
                    <td>${skill.name}</td>
                    <td><c:if test="${not empty skill.description}">${skill.description}</c:if></td>
                    <c:choose>
                        <c:when test="${not empty skill.category}">
                            <td>
                                <c:choose>
                                    <c:when test="${skill.category.colour != 'Blank'}">
                                        <c:set var="categoryColour" value="${skill.category.colour}" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="categoryColour" value="white" />
                                    </c:otherwise>
                                </c:choose>
                                <div class="skill-category-colour" style="background-color:${categoryColour}"></div>
                                ${skill.category.name}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${skill.category.appearOnBadge}">
                                        <span class="icon-ok">
                                    </c:when>
                                    <c:otherwise>
                                        <span class="icon-remove">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td><span class="icon-remove"></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
