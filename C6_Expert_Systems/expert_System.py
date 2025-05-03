# Assignment-C6 (Expert System)

def evaluate_employee():
    print("🔍 Employee Performance Evaluation System\n")

    # Input section
    attendance = input("1. Attendance (Good / Average / Poor): ").strip().lower()
    project = input("2. Project Completion (On Time / Delayed / Incomplete): ").strip().lower()
    teamwork = input("3. Teamwork (Excellent / Good / Poor): ").strip().lower()
    punctuality = input("4. Punctuality (Always on time / Often late): ").strip().lower()

    # Score system
    score = 0

    # Attendance score
    if attendance == "good":
        score += 3
    elif attendance == "average":
        score += 2
    elif attendance == "poor":
        score += 0

    # Project completion score
    if project == "on time":
        score += 3
    elif project == "delayed":
        score += 1
    elif project == "incomplete":
        score += 0

    # Teamwork score
    if teamwork == "excellent":
        score += 3
    elif teamwork == "good":
        score += 2
    elif teamwork == "poor":
        score += 0

    # Punctuality score
    if punctuality == "always on time":
        score += 2
    elif punctuality == "often late":
        score += 0

    # Decision logic
    print("\n📊 Evaluation Result:",score)
    
    if score >= 9:
        print("⭐ Performance: Excellent")
    elif score >= 6:
        print("✅ Performance: Good")
    elif score >= 3:
        print("⚠️ Performance: Needs Improvement")
    else:
        print("❌ Performance: Poor")

# Run the expert system
evaluate_employee()
