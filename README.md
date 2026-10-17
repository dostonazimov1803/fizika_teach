# «Fizika darslari» ilovasini Play Market'ga joylash

Kompyuterga Android Studio o'rnatish shart emas: ilova fayli GitHub serverlarida bepul yig'iladi. Sizga kompyuter yoki noutbuk, Gmail va bitta Android telefon kerak.

Qisqacha yo'l: **GitHub → ilova fayli (AAB) → Play Console → 12 testerli sinov (14 kun) → nashr**.

---

## 1-qadam. Google Play Console akkaunti

1. play.google.com/console saytiga Gmail bilan kiring va **Personal** (shaxsiy) akkaunt oching.
2. Bir martalik to'lov — **25 AQSh dollari** (Visa yoki Mastercard karta).
3. Pasport bilan shaxsingizni tasdiqlang. Telefon raqamini ham tasdiqlang.
4. Tasdiqlash bir necha kun davom etishi mumkin. Shu orada keyingi qadamlarni bajaravering.

## 2-qadam. GitHub'ga loyihani yuklash

1. github.com da bepul akkaunt oching.
2. **New repository** tugmasini bosing. Nom bering, masalan `fizika-darslari`. **Private** yoki **Public** — 5-qadamga qarang.
3. `fizika-darslari-android.zip` ni oching. Ichidagi **barcha** fayl va papkalarni repozitoriyaga yuklang.
   - Eng oson yo'l — **GitHub Desktop** dasturi. Brauzer orqali ham bo'ladi: «uploading an existing file» ni bosib, papkalarni sudrab tashlang.
   - `.github` papkasi ham yuklanganini albatta tekshiring. Ilovani avtomatik yig'adigan sozlama aynan shu papkada. Windows'da u yashirin ko'rinishi mumkin.

## 3-qadam. Imzo kalitini GitHub'ga kiritish

Play Market faqat sizning kalitingiz bilan imzolangan faylni qabul qiladi.

Repozitoriyada **Settings → Secrets and variables → Actions → New repository secret** ni oching va ikkita «secret» qo'shing:

| Name | Value |
|---|---|
| `KEYSTORE_BASE64` | `keystore-base64.txt` faylining butun matni |
| `KEYSTORE_PASSWORD` | `keystore-password.txt` dagi parol |

⚠️ `upload-keystore.jks` faylini va parolni xavfsiz joyda saqlang: flesh, Google Drive va hokazo. Ular ilovani yangilashda ham kerak bo'ladi. Hech kimga bermang va repozitoriyaga **yuklamang**.

## 4-qadam. Ilova faylini olish

1. Repozitoriyada **Actions** bo'limini oching, so'ng **Android ilovani yig'ish → Run workflow** ni bosing.
2. Taxminan 5–10 daqiqa kuting, belgi yashil bo'lishi kerak.
3. Pastdagi **Artifacts** bo'limidan ikkita fayl yuklab oling:
   - **sinov-apk** — `app-debug.apk` ni telefoningizga o'rnatib sinab ko'ring.
   - **play-market-aab** — `app-release.aab`. Play Console'ga aynan shu fayl yuklanadi.

Agar belgi qizil bo'lsa, xato matnini nusxalab menga yuboring — tuzatib beraman.

## 5-qadam. Maxfiylik siyosati havolasi

Play Market maxfiylik siyosati havolasini majburiy so'raydi. Sahifa tayyor: `docs/privacy.html`.

- **Repozitoriya Public bo'lsa:** Settings → Pages → *Deploy from a branch* → `main`, papka `/docs` → Save. Bir necha daqiqadan keyin havola tayyor bo'ladi: `https://FOYDALANUVCHI-NOMI.github.io/fizika-darslari/privacy.html`. Bonus: `https://FOYDALANUVCHI-NOMI.github.io/fizika-darslari/` manzilida ilovaning **sayt versiyasi** ham ochiladi.
- **Repozitoriya Private bo'lsa:** bepul tarifda GitHub Pages ishlamaydi. Unda sites.google.com da bitta sahifa oching va `privacy.html` matnini o'sha yerga ko'chiring.

## 6-qadam. Play Console'da ilova yaratish

1. **Create app** → nomi: `Fizika darslari`. Default language: **Russian – ru** (o'zbek tili ro'yxatda yo'q). App, Free.
2. **Dashboard → Set up your app** bo'limidagi formalarni to'ldiring. Barcha javoblar `PLAY-MARKET-MATNLAR.md` jadvalida tayyor.
3. **Store listing** bo'limiga matnlarni va rasmlarni yuklang: ikonka, banner, 8 ta skrinshot.

## 7-qadam. Yopiq sinov (yangi akkauntlar uchun majburiy)

2023-yil noyabridan keyin ochilgan shaxsiy akkauntlar ilovani darhol hammaga chiqara olmaydi:

1. **Testing → Closed testing → Create track**. `app-release.aab` ni yuklang.
2. **Testers** bo'limida kamida **12 ta** Gmail manzilini kiriting: hamkasblar, tanishlar.
3. Ular havola orqali ilovani o'rnatib, **14 kun ketma-ket** testda qolishi kerak.
4. 14 kundan keyin **Dashboard → Apply for production** ni bosing. Google'ning ko'rib chiqishi odatda taxminan 7 kun davom etadi.
5. Ruxsat berilgach: **Production → Create release** → o'sha AAB → **Rollout**.

## Keyinchalik yangilash

Kontentni o'zgartirsangiz, quyidagilarni bajaring:

1. Yangi `index.html` ni `app/src/main/assets/` ga qo'ying.
2. `app/build.gradle` da `versionCode` ni bittaga oshiring (1 → 2), `versionName` ni ham o'zgartiring (`1.1`).
3. GitHub'ga yuklang. Actions yangi AAB yasaydi, uni Play Console'ga yangi reliz sifatida yuklaysiz.

---

**Texnik ma'lumot:** paket nomi `uz.fizika.darslar`, minSdk 26 (Android 8+), targetSdk 36 (Android 16 — Play'ning 2026-yildagi talabi). Ilovada ruxsatlar, reklama va ma'lumot yig'ish yo'q. Paket nomini Play'ga birinchi yuklashdan oldin o'zgartirish mumkin, keyin esa abadiy o'zgarmaydi.
