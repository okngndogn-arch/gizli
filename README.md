# Rayzgâr

Çocuklar için telefonda parmakla oynanan, tarayıcıda çalışan 3D sonsuz koşu oyunu.

Oyunun tamamı tek bir dosyada: `index.html`. 3D çizim için [Three.js](https://threejs.org) (0.149.0) CDN üzerinden yüklenir, yazı tipleri Google Fonts'tan gelir. Bunun dışında hiçbir dış dosya, model ya da ses dosyası yoktur. Modeller kodla oluşturulur, sesler Web Audio ile üretilir.

## Nasıl açılır

- **Bilgisayarda:** `index.html` dosyasını bir tarayıcıda açman yeterli (internet bağlantısı gerekir).
- **Telefonda:** GitHub Pages'i açıp linki telefonda kullan. Ayarlar → Pages → Branch: `main` / `(root)` → Save. Birkaç dakika sonra oyun `https://okngndogn-arch.github.io/gizli/` adresinde açılır.

## Android uygulaması (APK)

Hazır APK: [`apk/Rayzgar-1.2.apk`](apk/Rayzgar-1.2.apk). Dosyayı telefona indirip aç. Android "bilinmeyen kaynaklardan yükleme" izni isteyebilir. Uygulama internet bağlantısı olmadan çalışır.

`android/` klasörü uygulamanın kaynağıdır: oyunu tam ekran bir WebView'de açan küçük bir Android uygulaması. `android/assets/www/index.html`, oyunun internetsiz sürümüdür (Three.js ve yazı tipleri içine konmuştur). Gradle gerekmez, doğrudan Android SDK araçlarıyla derlenir:

```bash
ANDROID_HOME=/yol/android-sdk KS=/yol/rayzgar.jks KS_PASS=sifre ./android/build.sh
```

Gerekenler: Java 17+, Android SDK `platforms;android-34` ve `build-tools;34.0.0`. İmza anahtarı (`.jks`) depoya konmaz. Güncellemeler aynı anahtarla imzalanmalıdır, yoksa telefondaki uygulama güncellenemez.

## Kontroller

| Hareket | Telefon | Bilgisayar |
|---|---|---|
| Şerit değiştir | Sağa / sola kaydır | ← → ya da A / D |
| Zıpla | Yukarı kaydır | ↑, W ya da Boşluk |
| Eğil, kay | Aşağı kaydır | ↓ ya da S |
| Kaykaya bin | Ekrana çift dokun | B ya da çift tıklama |

## İçindekiler

- 13 koşucu, 14 kostüm, 13 kaykay ve 11 dünya içeren market (toplanan altınla satın alınır)
- Her koşucunun kendine has bir yeteneği (örneğin Mırmır daha yükseğe zıplar, Pırıl koşuya kalkanla başlar)
- Güçlendirmeler: roket, mıknatıs, kaykay, 2 kat puan, kalkan ve süper zıplama
- İçinden altın, güçlendirme ya da kaykay hakkı çıkan sürpriz kutular
- Uzun karanlık tüneller, nehir köprüleri, karşıdan gelen vagonlar ve şeritler arasında gidip gelen bariyerler
- 7 günlük günlük ödül takvimi, her gün yenilenen 3 görev ve 20 kalıcı rozet
- Her dünyanın kendi müziği; ses, müzik ve titreşim ayarları
- İlk oyunda ağır çekimli, el animasyonlu rehber
- İki çarpmada düşme sistemi ve oyun başına bir "Bir Şans Daha" hakkı

İlerleme (altın, satın alınanlar, rekor, görevler) tarayıcının yerel depolamasında (`localStorage`) tutulur. Başka cihaza ya da tarayıcıya taşınmaz.
