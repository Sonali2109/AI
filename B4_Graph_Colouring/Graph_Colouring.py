def is_valid(node, color, assign, graph):
    return all(assign.get(nei) != color for nei in graph[node])

def bnb(assign, graph, colors, min_used, best):
    if len(assign) == len(graph):
        used = set(assign.values())
        if len(used) < min_used[0]:
            min_used[0] = len(used)
            best.clear()
            best.update(assign)
        return

    node = next(n for n in graph if n not in assign)
    for color in colors:
        if is_valid(node, color, assign, graph):
            assign[node] = color
            if len(set(assign.values())) <= min_used[0]:
                bnb(assign, graph, colors, min_used, best)
            assign.pop(node)

def solve(graph, colors):
    assign, best = {}, {}
    bnb(assign, graph, colors, [len(colors) + 1], best)
    return best or None

def input_graph_colors():
    graph = {}
    nodes = input("Enter node names (space-separated): ").split()
    for node in nodes:
        graph[node] = input(f"Enter neighbors of {node} (space-separated): ").split()
    colors = input("Enter colors (space-separated): ").split()
    return graph, colors

# Menu loop
graph = colors = result = None
while True:
    print("\n--- Graph Coloring Menu ---")
    print("1. Input Graph & Colors")
    print("2. Solve")
    print("3. Exit")

    choice = input("Enter your choice: ").strip()
    if choice == '1':
        graph, colors = input_graph_colors()
    elif choice == '2':
        if graph and colors:
            result = solve(graph, colors)
            if result:
                print("\nColor Assignment:")
                for node, color in result.items():
                    print(node + ": " + color)
                print("Colors Used: " + str(len(set(result.values()))) + " / " + str(len(colors)))
            else:
                print("No valid coloring found.")
        else:
            print("Please input the graph and colors first.")
    elif choice == '3':
        print("Exiting program...")
        break
    else:
        print("Invalid choice. Try again.")