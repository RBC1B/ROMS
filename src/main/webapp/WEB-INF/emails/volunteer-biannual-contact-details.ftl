Dear <#if volunteer.gender??><#if volunteer.gender == 'M'>Brother <#else>Sister </#if></#if>${volunteer.person.forename} ${volunteer.person.surname}

This email is to inform you of the information that the RBC keeps of you.

On our system, your contact details are as follows:

Address:
${street},
${town},
<#if county ??>
${county},
</#if>
${postcode}

Email:
${person.email}

Home Telephone:
<#if person.telephone ??>
${person.telephone}
<#else>
We do not have a record of your home telephone.
</#if>

Mobile phone:
<#if person.mobile ??>
${person.mobile}
<#else>
We do not have a record of your mobile phone.
</#if>

Work phone:
<#if person.workPhone ??>
${person.workPhone}
<#else>
We do not have a record of your work phone.
</#if>

If any of the details above are incorrect, then please use the Edifice link below to make any updates.
When you click on the link, please click on the button "Update contact details". Edifice will then
prompt you for your RBC ID number and your date of birth. Your RBC ID number is: ${person.personId}.
Edifice URL: ${edificeHomeUrl}

However, if the above details are correct, then please confirm this by using the link below:

${confirmationUrl}

We also have the following information about you on our records. If there are any changes in the
information below, then please contact the Volunteer Services Department via your Department Overseer: 

Congregation:
${congregation}

<#if emergencyContact?? >
Your Emergency Contact:
${emergencyContact.forename} ${emergencyContact.surname}
Address:
<#if emergencyContact.address?? >
${emergencyContact.address.street},
${emergencyContact.address.town},
<#if emergencyContact.address.county ??>
${emergencyContact.address.county},
</#if>
${emergencyContact.address.postcode}
<#else>
An address for your emergency contact has not been provided.
</#if>
Emergency Contact Phone numbers:
<#if emergencyContact.telephone?? >
${emergencyContact.telephone}
<#else>
A telephone number for your emergency contact has not been provided.
</#if>
<#if emergencyContact.mobile?? >
${emergencyContact.mobile}
<#else>
A mobile number for your emergency contact has not been provided.
</#if>
<#else>
You have not provided an emergency contact. Please inform the Volunteer Service Department as this important.
</#if>

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