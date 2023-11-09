# Pahada Android Foundations
Android library for Whympr and Iphigenie Android applications.

## Features
- `ApiRepository.safeApiCall`
- MockWebServer based unit tests

## Publish
- In `build.gradle`, bump the:
  - version name (`android.defaultConfig.versionName`) (publish will fail if the version name already exists)
  - and code (`android.defaultConfig.versionCode`)

- `./gradlew publish`
