Simple test app using Apollo, GraphQL and the Github API. App follows MVVM architecture pattern.

To use the app simply substitute "YOUR_TOKEN" in gradle.properties for a generated personal access token (needed to use Github's API)

For the first and second query you simply introduce an organization's name and press SEARCH, they give back the total number of repositories and the biggest repository for that organization, respectively. The third one returns the total amount of organizations in github, it doesn't need any additional inputs, just press REFRESH.