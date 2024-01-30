<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Aktivasi Aplikasi BRKRIS Bank Riau Kepri</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td colspan="2" align="center" bgcolor="#ffac654" style="padding: 40px 0 30px 0;" >
                  <img src="cid:logo.png" alt="QRIS Bank Riau Kepri" style="display: block;" width="50" height="60"/> 
                <!-- <img th:src="@{/images/logo.png}" alt="QRIS Bank Riau Kepri" style="display: block;" width="20%" height="20%"/> -->
            </td>
        </tr>
        <tr>
            <td colspan="2" bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
                <p>Kepada Yth. <b>${name}</b>,</p>
                <p>Untuk keamanan data anda, jangan berikan kode ini kesiapapun, termasuk pihak yang mengaku dari Bank Riau Kepri.</p>
				<p>Kode Verifikasi : <b>${kode_aktivasi}</b></p>
				<p>Gunakan kode verifikasi ini untuk melakukan reset ${jenis} anda.
                <p>Abaikan email ini jika kamu tidak merasa sedang melakukan permintaan pengubahan ${jenis}</p> 
				<p>Terimakasih.</p>
            </td>
        </tr>
        <tr>
            <td colspan="2" bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
                <p><i>Email ini dikirimkan secara otomatis oleh sistem QRIS Merchant BRK, kami tidak melakukan pengecekan email yang dikirimkan ke email ini. Jika ada pertanyaan, silahkan hubungi <b>(0761) 47070</b></i></p>
            </td>
        </tr>
		<tr>
            <td bgcolor="#B12126" style="padding: 10px 75px"> </td>
			<td bgcolor="#FAA61A" style="padding: 10px 25px"> </td>
        </tr>
    </table>

</body>
</html>