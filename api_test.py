#!/usr/bin/env python
# -*- coding: utf-8 -*-

import requests
import sys
from colorama import init, Fore

init(autoreset=True)


def print_status_code(status_code):
    if status_code / 100 == 2:
        print(Fore.GREEN + str(status_code) + ' -> OK')
    elif status_code == 403:
        print(Fore.RED + str(status_code) + ' -> FORBIDDEN')
    elif status_code == 404:
        print(Fore.YELLOW + str(status_code) + ' -> NOT_FOUND')
    elif status_code == 400:
        print(Fore.YELLOW + str(status_code) + ' -> BAD REQUEST')


def main(args):
    base_url = args[0] if len(args) >= 1 else 'http://localhost:8080'
    customer_id = 1
    session = requests.Session()

    print(Fore.CYAN + "Getting customer with ID: {0}").format(customer_id)
    r = session.get(base_url + '/customers/{0}'.format(customer_id))
    print_status_code(r.status_code)
    resp = r.json()
    customer_name = resp['name']
    print('\t' + Fore.GREEN + "Name: {0} | Bonus points: {1}".format(
          customer_name, resp['points']))

    print(Fore.CYAN + "Getting film IDs")
    r = session.get(base_url + '/films')
    print_status_code(r.status_code)
    film_ids = [film['id'] for film in r.json()]
    days = [1, 5, 2, 7]

    print(Fore.CYAN + "Getting rental historic")
    r = session.get(base_url + '/rentals')
    print_status_code(r.status_code)
    rentals = r.json()
    rental_ids = [rental['id'] for rental in rentals]

    for r in rentals:
        print('\t' + Fore.YELLOW + r['film']['name'])

    print(Fore.CYAN + "Renting Matrix 11, Spiderman, Spiderman2 and Out of Africa")
    payload = [{'filmId': film_ids[i], 'days': days[i]} for i in range(len(film_ids))]
    r = session.post(base_url + '/customers/{0}/rent'.format(customer_id), json=payload)
    print_status_code(r.status_code)
    resp = r.json()
    print('\t' + Fore.GREEN + "Price: {0} {1} | Bonus points: {2} :)".format(
          resp['total']['value'], resp['total']['currency']['symbol'], resp['bonusPoints']))

    print(Fore.CYAN + "Returning seeded rentals")
    payload = [{'rentalId': r_id} for r_id in rental_ids]
    r = session.post(base_url + '/customers/{0}/return'.format(customer_id), json=payload)
    print_status_code(r.status_code)
    resp = r.json()
    print('\t' + Fore.RED + "Surcharge: {0} :(".format(
          resp['total']['value'], resp['total']['currency']['symbol']))

    print(Fore.CYAN + "Getting {0} updated info").format(customer_name)
    r = session.get(base_url + '/customers/{0}'.format(customer_id))
    print_status_code(r.status_code)
    resp = r.json()
    print('\t' + Fore.GREEN + "Bonus points: {1}".format(
          customer_name, resp['points']))


if __name__ == "__main__":
    main(sys.argv[1:])
