Dear <#if volunteer.gender??><#if volunteer.gender == 'M'>Brother <#else>Sister </#if></#if>${volunteer.person.forename} ${volunteer.person.surname}

This email is to inform you of the information that the RBC keeps of you.

On our system, your contact details are as follows:

Address:
${street},
${town},
${county},
${postcode}

Email:
${person.email}

Home Telephone:
${person.telephone}

Mobile phone:
${person.mobile}

<#if person.workPhone??>Work phone:
${person.workPhone}
<#else>
A work phone has not been provided.
</#if>

If any of the details above are incorrect, then please use this link to make any necessary updates:
<#-- httpsUrl -->

However, if the above details are correct, then please confirm this by using the link below:

<#-- confirm link -->

We also have the following information about you on our records. If there are any changes in the
information below, then please contact the Volunteer Services Department via your Department Overseer: 

Congregation:
${person.congregation.name}

Your Emergency Contact:
${emergencyContact.forename} ${emergencyContact.surname}
Address:
${emergencyContact.address.street},
${emergencyContact.address.town},
${emergencyContact.address.county},
${emergencyContact.address.postcode}
Emergency Contact Phone numbers:
${emergencyContact.telephone}
${emergencyContact.mobile}

<#-- assignments -->
Your Departmental Assignments:
<#list assignments as assignment>
 - Department: ${assignment.department.name}, Role: ${assignment.role}, Trade Number: ${assignment.tradeNumber}
</#list>

<#-- qualifications -->
Your Qualifications:
<#if qualifications?has_content>
<#list qualifications as qualification>
 - ${qualification.qualification.name}
</#list>
<#else>
We currently do not have any information about your qualifications.
</#if>

<#-- skills -->
Your Skills:
<#if skills?has_content>
<#list skills as skill>
 - ${skill.skill.name} (Dept: ${skill.department.name})
</#list>
<#else>
We currently do not have any information about your skills.
</#if>

Kind Regards
Volunteer Service Department

NOTE TO COORIDINATOR

As many change their email addresses frequently please check with the volunteer that they have received this email. If the volunteer is no longer in your congregation please notify us using the mail address given above.
