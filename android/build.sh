#!/usr/bin/env bash
# Rayzgâr APK derleme betiği (Gradle kullanmadan, doğrudan Android SDK araçlarıyla)
# Kullanım: ANDROID_HOME=/yol/android-sdk KS=anahtar.jks KS_PASS=sifre ./build.sh
set -euo pipefail
cd "$(dirname "$0")"
SDK="${ANDROID_HOME:?ANDROID_HOME gerekli}"
BT="$SDK/build-tools/34.0.0"
PJ="$SDK/platforms/android-34/android.jar"
VERSION_CODE="${VERSION_CODE:-5}"
VERSION_NAME="${VERSION_NAME:-1.4}"
rm -rf build && mkdir -p build/gen build/classes build/dex
"$BT/aapt2" compile --dir res -o build/res.zip
"$BT/aapt2" link -o build/base.apk -I "$PJ" --manifest AndroidManifest.xml -A assets \
  --java build/gen build/res.zip --min-sdk-version 24 --target-sdk-version 34 \
  --version-code "$VERSION_CODE" --version-name "$VERSION_NAME"
javac -source 8 -target 8 -nowarn -Xlint:-options -classpath "$PJ" -d build/classes \
  $(find build/gen src -name '*.java')
"$BT/d8" --min-api 24 --release --lib "$PJ" --output build/dex $(find build/classes -name '*.class')
cp build/base.apk build/unsigned.apk
(cd build/dex && zip -q -u ../unsigned.apk classes.dex)
"$BT/zipalign" -p -f 4 build/unsigned.apk build/aligned.apk
"$BT/apksigner" sign --ks "${KS:?KS gerekli}" --ks-pass "pass:${KS_PASS:?KS_PASS gerekli}" \
  --out "build/Rayzgar-$VERSION_NAME.apk" build/aligned.apk
"$BT/apksigner" verify --print-certs "build/Rayzgar-$VERSION_NAME.apk" | head -3
echo "APK hazır: build/Rayzgar-$VERSION_NAME.apk"
