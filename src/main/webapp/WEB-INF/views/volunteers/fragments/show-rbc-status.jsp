<%--
The contents of the rbc status tab.
Author: oliver.elder.esq
--%>
<dl class="dl-horizontal">
    <dt>Form date:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.formDate != null}">
                <fmt:formatDate value="${volunteer.formDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview date:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.interviewDate != null}">
                <fmt:formatDate value="${volunteer.interviewDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interviewers:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.interviewerA != null || $volunteer.interviewerB != null}">
                <c:if test="${volunteer.interviewerA != null}">
                    <a href="<c:url value='${volunteer.interviewerA.uri}' />" >${volunteer.interviewerA.forename} ${volunteer.interviewerA.surname}</a>
                </c:if>
                <c:if test="${volunteer.interviewerA != null && volunteer.interviewerB != null}">, </c:if>
                <c:if test="${volunteer.interviewerB != null}">
                    <a href="<c:url value='${volunteer.interviewerB.uri}' />" >${volunteer.interviewerB.forename} ${volunteer.interviewerB.surname}</a>
                </c:if>
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Interview comments:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.interviewComments != null}">
                ${volunteer.interviewComments}
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Joined date:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.joinedDate != null}">
                <fmt:formatDate value="${volunteer.joinedDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Badge issue date:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.badgeIssueDate != null}">
                <fmt:formatDate value="${volunteer.badgeIssueDate}" pattern="dd MMM yyyy" />
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
    <dt>Availability:</dt>
    <dd>
        <table id="volunteer-availability" class="table-bordered">
            <thead>
                <tr>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    <th>Sun</th>
                </tr>
            </thead>
            <tbody>
                <tr>
            <c:forEach var="i" begin="0" end="6">
                <td>
                <c:choose>
                    <c:when test="${volunteer.availability.get(i)}"><span class="icon-ok"></span></c:when>
                    <c:otherwise><span class="icon-remove"></span></c:otherwise>
                </c:choose>
                </td>
            </c:forEach>
            </tr>
            </tbody>
        </table>
    </dd>
    <dt>Oversight:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.oversight}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${volunteer.oversightComments != null}">
            (${volunteer.oversightComments})
        </c:if>
    </dd>
    <dt>Relief UK:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefUK}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${volunteer.reliefUKComments != null}">
            (${volunteer.reliefUKComments})
        </c:if>
    </dd>
    <dt>Relief Abroad:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.reliefAbroad}"><span class="icon-ok"></span></c:when>
            <c:otherwise><span class="icon-remove"></span></c:otherwise>
        </c:choose>
        <c:if test="${volunteer.reliefAbroadComments != null}">
            (${volunteer.reliefAbroadComments})
        </c:if>
    </dd>
    <dt>HHC Form code:</dt>
    <dd>
        <c:choose>
            <c:when test="${volunteer.hhcFormCode != null}">
                ${volunteer.hhcFormCode}
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
    </dd>
</dl>
<sec:authorize access="hasPermission('VOLUNTEER', 'EDIT')">
    <a class="btn btn-primary" href="<c:url value='${volunteer.editRbcStatusUri}' />">Edit</a>
</sec:authorize>
