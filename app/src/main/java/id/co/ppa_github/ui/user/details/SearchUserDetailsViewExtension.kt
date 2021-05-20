package id.co.ppa_github.ui.user.details

import id.co.ppa_github.databinding.LayoutUserDetailsBinding

infix fun LayoutUserDetailsBinding.setRepo(content: String?) {
    with(searchDetailsIncludeUserDetails.userDetailsIncludeRepo) {
        itemUserDetailsAdditionalInfoTvTitle setTextFrom "Repos"
        itemUserDetailsAdditionalInfoTvContent setTextFrom content
    }
}

infix fun LayoutUserDetailsBinding.setFollower(content: String?) {
    with(searchDetailsIncludeUserDetails.userDetailsIncludeFollower) {
        itemUserDetailsAdditionalInfoTvTitle setTextFrom this.root.context.resources.getString(R.string.label_follower)
        itemUserDetailsAdditionalInfoTvContent setTextFrom content
    }
}

infix fun LayoutUserDetailsBinding.setFollowing(content: String?) {
    with(searchDetailsIncludeUserDetails.userDetailsIncludeFollowing) {
        itemUserDetailsAdditionalInfoTvTitle setTextFrom this.root.context.resources.getString(R.string.label_following)
        itemUserDetailsAdditionalInfoTvContent setTextFrom content
    }
}

infix fun LayoutUserDetailsBinding.setGists(content: String?) {
    with(searchDetailsIncludeUserDetails.userDetailsIncludeGists) {
        itemUserDetailsAdditionalInfoTvTitle setTextFrom "Gists"
        itemUserDetailsAdditionalInfoTvContent setTextFrom content
    }
}