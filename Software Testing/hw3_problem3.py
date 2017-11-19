

def calculate_balance(number_of_shares, closing_price):
  index = 0
  rate = [0.0085, 0.0134, 0.02, 0.025, 0.0275, 0.035]
  fee = [50.0, 50.0, 0, 0, 0, 0]
  if number_of_shares <= 50:
    index = 0
  else:
    if number_of_shares < 300:
      index = 1
    else:
      if number_of_shares <= 750:
        index = 2
      else:
        if number_of_shares < 1000:
          index = 3
        else:
          if number_of_shares <= 2000:
            index = 4
          else:
            index = 5
  return number_of_shares*closing_price*(1 + rate[index]) - fee[index]



if __name__ == '__main__':
  print(calculate_balance(50, 140))
  print(calculate_balance(299, 140))
  print(calculate_balance(750, 140))
  print(calculate_balance(999, 140))
  print(calculate_balance(1999, 140))
  print(calculate_balance(2000, 140))
  print(calculate_balance(51, 140))
  print(calculate_balance(300, 140))
  print(calculate_balance(751, 140))
  print(calculate_balance(1000, 140))
  print(calculate_balance(0, 140))
  print(calculate_balance(10000, 140))
