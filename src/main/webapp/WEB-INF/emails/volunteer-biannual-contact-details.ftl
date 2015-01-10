Dear <#if volunteer.gender??><#if volunteer.gender == 'M'>Brother <#else>Sister </#if></#if>${volunteer.person.forename} ${volunteer.person.surname}

This email is to inform you of the information that the RBC keeps of you.

On our system, your contact details are as follows:

Address:
<#-- ${address.street},
${volunteer.person.address.town},
${volunteer.person.address.county},
${volunteer.person.address.postcode} -->

Email:
${volunteer.person.email}

Home Telephone:
${volunteer.person.telephone}

Mobile phone:
${volunteer.person.mobile}

<#if volunteer.person.workPhone??>Work phone:
${volunteer.person.workPhone}
<#else>
A work phone has not been provided.
</#if>

If any of the details above are incorrect, then please use this link to make any necessary updates:
<#-- httpsUrl -->

However, if the above details are correct, then please confirm this by using the link below:

<#-- confirm link -->

We also have the following information about you on our records. If there are any changes in the
information below, then please email the Volunteer Services Department: 

Congregation:
<#-- ${volunteer.person.congregation.name} -->

Your Emergency Contact:
<#-- ${volunteer.emergencyContact.forename} ${volunteer.emergencyContact.surname} -->
Address: 
<#-- ${volunteer.emergencyContact.address.street} 
${volunteer.emergencyContact.address.town}
${volunteer.emergencyContact.address.county}
${volunteer.emergencyContact.address.postcode} -->
Phone numbers:
<#-- ${volunteer.emergencyContact.telephone}
${volunteer.emergencyContact.mobile} -->

<#-- trades -->

<#-- qualifications -->

<#-- skills -->

If any of the details above, such as your emergency contact details, your skills


Kind Regards
Volunteer Service Department

NOTE TO COORIDINATOR

As many change their email addresses frequently please check with the volunteer that they have received this email. If the volunteer is no longer in your congregation please notify us using the mail address given above.
