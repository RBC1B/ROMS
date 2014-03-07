Dear <#if volunteer.gender??><#if volunteer.gender == 'M'>Brother <#else>Sister </#if></#if>${volunteer.person.forename} ${volunteer.person.surname}

You have been invited to an RBC induction interview at the time and place shown below:


${sessionDate?string("d MMM yyyy")}, ${sessionTime}
${kingdomHallName}
${kingdomHallAddress.format()}

If you are unable to attend this session, please contact the RBC Volunteer Service Department
on the details below.


The details we have from your form are:

Name: ${volunteer.person.forename}<#if volunteer.person.middlename??> ${volunteer.person.middlename}</#if> ${volunteer.person.surname}
Address: ${(volunteer.person.address.format())!"-"}
Home phone: ${(volunteer.person.telephone)!"-"}
Mobile Phone: ${(volunteer.person.mobile)!"-"}
Congregation: ${volunteerCongregationName!"-"} 

If any of these details are incorrect, please contact the RBC Volunteer Service Department
on the details below so we can correct them before your interview.

Kind Regards
FIXME: {RBC name} Volunteer Service Department

Email: FIXME: {vsd email}
Phone: FIXME: {vsd phone}
