# Prim's Algorithm for Minimum Spanning Tree
# adjacency matrix with -1 representing no edge between vertices
graph = [
 [-1, 3, 2, 8, -1],
 [3, 5, -1, 7, -1],
 [2, -1, -1, 4, 9],
 [8, 7, 4, -1, 6],
 [-1, -1, 9, 6, -1]
]
def prims_algorithm(graph):
 n = len(graph)

 visited = [0] * n
 visited[0] = 1
 min_cost = 0

 for _ in range(n - 1):
 min_weight = float('inf')
 row = col = -1
 for i in range(n):
 for j in range(n):
 if visited[i] == 1 and visited[j] == 0 and graph[i][j]
!= -1:
 if graph[i][j] < min_weight:
 min_weight = graph[i][j]
 row, col = i, j
 visited[col] = 1
 min_cost += min_weight

 print(f"\nEdge {row + 1} - {col + 1} : Weight = {min_weight}")

 print(f"\nMinimum cost of the spanning tree is {min_cost}.\n")

prims_algorithm(graph)
