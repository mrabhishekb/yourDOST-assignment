def remove_duplicates(nums):
    dict={}
    for num in nums:
        dict[num]=dict.get(num,0)+1
    return [num for num in nums if dict[num]==1]

def get_second_largest_num(nums):
    if(len(nums)==0):
        return -1
    maxi=float('-inf')
    maxi_2=float('-inf')
    for num in nums:
        if num>maxi:
            maxi_2=maxi
            maxi=num
        elif num<maxi and num>maxi_2:
            maxi_2=num
    return maxi_2 if maxi_2 != float('-inf') else -1


if __name__=='__main__':
    while(True):
        nums = list(map(int, input("Enter numbers separated by space: ").split()))
        dup_removed = remove_duplicates(nums)
        print("the second largest unique number is:", get_second_largest_num(dup_removed))
        choice = input("Do you want to continue? (y/n): ")
        if(choice!='y'):
            break