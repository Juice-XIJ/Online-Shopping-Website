/*
    This is a solution for Leetcode 407 Trapping Rain Water II. It's a hard question and different from Trapping Rain Water.
    In my code, I could divide the question into two easy parts and solve them one by one.
    Also the code is clean and with good naming. It's easy to read and understand along the short comments.
*/
class Solution {
private:
    struct Cell {
        // This is to store the information of a cell, x and y are the coordinates and h is the high
        int x, y, h;
        Cell(int a, int b, int c) : x(a), y(b), h(c) {}
    };

    struct cmp {
        bool operator () (Cell *A, Cell *B) {
            return A->h > B->h;
        }
    };
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        /*
            In this question, I would like to use heap and dfs to solve it.
            1. I firstly push the boundary into the heap and the heap keep lowest cell on the top.
            2. Then keeping push the neighbors of the top cell into the heap and use a matrix "used" to track which cell has been processed.
        */
        if (heightMap.size() == 0 || heightMap[0].size() == 0) {
            return 0;
        }
        int row = heightMap.size();
        int col = heightMap[0].size();
        vector<vector<int>> used(row, vector<int>(col, 0));
        priority_queue<Cell*, vector<Cell*>, cmp> q;

        // Initialize the heap
        for (int i = 0; i < row; i++) {
            q.push(new Cell(i, 0, heightMap[i][0]));
            used[i][0] = 1;
            q.push(new Cell(i, col - 1, heightMap[i][col - 1]));
            used[i][col - 1] = 1;
        }
        for (int i = 1; i < col - 1; i++) {
            q.push(new Cell(0, i, heightMap[0][i]));
            used[0][i] = 1;
            q.push(new Cell(row - 1, i, heightMap[row - 1][i]));
            used[row - 1][i] = 1;
        }

        // Direction arrays.
        vector<int> deltaX{0,  0, 1, -1};
        vector<int> deltaY{1, -1, 0,  0};

        int sum = 0;
        while (!q.empty()) {
            auto t = q.top();
            q.pop();
            // Got the top of the stack and tried to walk to its four direction
            for (int i = 0; i < 4; i++) {
                int x = t->x + deltaX[i];
                int y = t->y + deltaY[i];
                if (x >= 0 && x < row && y >= 0 && y < col && used[x][y] == 0) {
                    // If its neighbor is higher then itself, the neighbor could not keep water at that time.
                    sum += max(0, t->h - heightMap[x][y]);
                    q.push(new Cell(x, y, max(t->h, heightMap[x][y])));
                    used[x][y] = 1;
                }
            }
        }

        return sum;
    }
};
