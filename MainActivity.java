name: Android ilovani yig'ish

on:
  push:
    branches: [ main ]
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'

      - uses: gradle/actions/setup-gradle@v4

      - name: Kalitni tiklash (upload key)
        env:
          KEYSTORE_BASE64: ${{ secrets.KEYSTORE_BASE64 }}
        run: |
          if [ -n "$KEYSTORE_BASE64" ]; then
            echo "$KEYSTORE_BASE64" | base64 -d > "$RUNNER_TEMP/upload-keystore.jks"
            echo "KEYSTORE_FILE=$RUNNER_TEMP/upload-keystore.jks" >> "$GITHUB_ENV"
          else
            echo "::warning::KEYSTORE_BASE64 secret topilmadi — release fayllar imzolanmaydi"
          fi

      - name: Yig'ish
        env:
          KEYSTORE_PASSWORD: ${{ secrets.KEYSTORE_PASSWORD }}
          KEY_PASSWORD: ${{ secrets.KEYSTORE_PASSWORD }}
          KEY_ALIAS: upload
        run: |
          chmod +x gradlew
          ./gradlew --no-daemon assembleDebug bundleRelease assembleRelease

      - name: Play Market uchun AAB
        uses: actions/upload-artifact@v4
        with:
          name: play-market-aab
          path: app/build/outputs/bundle/release/*.aab

      - name: Telefonda sinash uchun APK
        uses: actions/upload-artifact@v4
        with:
          name: sinov-apk
          path: |
            app/build/outputs/apk/debug/*.apk
            app/build/outputs/apk/release/*.apk
