def sprawdz_plec(pesel):
    """
    nazwa funkcji:
    **********************************************
    sprawdz_plec
    opis funkcji:
    Sprawdza płeć osoby na podstawie przedostatniej cyfry numeru PESEL.
    parametry:
    pesel - numer PESEL przechowywany jako tekst
    zwracany typ i opis:
    str - zwraca 'K' dla kobiety lub 'M' dla mężczyzny
    autor:
    <numer zdającego>
    **********************************************
    """
    if int(pesel[9]) % 2 == 0:
        return 'K'
    else:
        return 'M'


def sprawdz_sume_kontrolna(pesel):
    wagi = [1, 3, 7, 9, 1, 3, 7, 9, 1, 3]

    S = 0

    for i in range(10):
        S += int(pesel[i]) * wagi[i]

    M = S % 10

    if M == 0:
        R = 0
    else:
        R = 10 - M

    return R == int(pesel[10])


pesel = input("Podaj numer PESEL: ")

plec = sprawdz_plec(pesel)

if plec == 'K':
    print("Płeć: Kobieta")
else:
    print("Płeć: Mężczyzna")


if sprawdz_sume_kontrolna(pesel):
    print("Suma kontrolna jest zgodna.")
else:
    print("Suma kontrolna jest niezgodna.")
