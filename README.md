# Rayzgâr

Çocuklar için telefonda parmakla oynanan, tarayıcıda çalışan 3D sonsuz koşu oyunu.

Oyunun tamamı tek bir dosyada: `index.html`. 3D çizim için [Three.js](https://threejs.org) (0.149.0) CDN üzerinden yüklenir, yazı tipleri Google Fonts'tan gelir. Bunun dışında hiçbir dış dosya, model ya da ses dosyası yoktur. Modeller kodla oluşturulur, sesler Web Audio ile üretilir.

## Nasıl açılır

- **Bilgisayarda:** `index.html` dosyasını bir tarayıcıda açman yeterli (internet bağlantısı gerekir).
- **Telefonda:** GitHub Pages'i açıp linki telefonda kullan. Ayarlar → Pages → Branch: `main` / `(root)` → Save. Birkaç dakika sonra oyun `https://okngndogn-arch.github.io/gizli/` adresinde açılır.

## Kontroller

| Hareket | Telefon | Bilgisayar |
|---|---|---|
| Şerit değiştir | Sağa / sola kaydır | ← → ya da A / D |
| Zıpla | Yukarı kaydır | ↑, W ya da Boşluk |
| Eğil, kay | Aşağı kaydır | ↓ ya da S |
| Kaykaya bin | Ekrana çift dokun | B ya da çift tıklama |

## İçindekiler

- 13 koşucu, 14 kostüm, 13 kaykay ve 11 dünya içeren market (toplanan altınla satın alınır)
- Roket, mıknatıs ve kaykay güçlendirmeleri
- 7 günlük günlük ödül takvimi ve her gün yenilenen 3 görev
- İlk oyunda ağır çekimli, el animasyonlu rehber
- İki çarpmada düşme sistemi ve oyun başına bir "Bir Şans Daha" hakkı

İlerleme (altın, satın alınanlar, rekor, görevler) tarayıcının yerel depolamasında (`localStorage`) tutulur. Başka cihaza ya da tarayıcıya taşınmaz.
