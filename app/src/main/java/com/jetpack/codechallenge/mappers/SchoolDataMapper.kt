package com.jetpack.codechallenge.mappers

import com.jetpack.codechallenge.models.SchoolDetails
import com.jetpack.codechallenge.ui.home.models.SchoolItem


class SchoolDataMapper {
    fun mapSchoolItem(remoteSchools: SchoolDetails): SchoolItem {
        return SchoolItem(
            name = remoteSchools.school_name,
            dbn = remoteSchools.dbn,
            address = schoolAddress(remoteSchools)
        )
    }

    private fun schoolAddress(remoteSchools: SchoolDetails) =
        remoteSchools.primary_address_line_1.plus(",").plus(remoteSchools.city.plus(","))
            .plus(remoteSchools.zip)

}