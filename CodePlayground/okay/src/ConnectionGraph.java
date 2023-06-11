import java.util.*;
import java.io.*;

public class ConnectionGraph {
    private LinkedList<Vertex> vertices;
    private int index;

    public ConnectionGraph() {
        vertices = new LinkedList<>();
        index = 0;
    }

    public void addVertex(User user) {
        if (!vertexExists(user)) {
            vertices.add(new Vertex(user, index++));
        }
    }

    public void addEdge(User user1, User user2) {
        Vertex vertex1 = getVertex(user1);
        Vertex vertex2 = getVertex(user2);

        if (vertex1 != null && vertex2 != null) {
            vertex1.addNeighbor(vertex2);
            vertex2.addNeighbor(vertex1);
        }
    }

    public void removeVertex(User user) {
        Vertex vertex = getVertex(user);
        if (vertex != null) {
            vertices.remove(vertex);
            // Remove connections to the vertex
            for (Vertex v : vertices) {
                v.removeNeighbor(vertex);
            }
        }
    }

    public void removeEdge(User user1, User user2) {
        Vertex vertex1 = getVertex(user1);
        Vertex vertex2 = getVertex(user2);
    
        if (vertex1 != null && vertex2 != null) {
            vertex1.removeNeighbor(vertex2);
            vertex2.removeNeighbor(vertex1);
        }
    }
    

    //method to check whether the edge are being made or not
    public boolean hasEdge(User user1, User user2) {
        Vertex vertex1 = getVertex(user1);
        Vertex vertex2 = getVertex(user2);
    
        if (vertex1 != null && vertex2 != null) {
            LinkedList<Vertex> neighbors = vertex1.getNeighbors();
            for (Vertex neighbor : neighbors) {
                if (neighbor.equals(vertex2)) {
                    return true;
                }
            }
        }
    
        return false;
    }

    
    public String showFirstDegreeConnections(User user) {
        StringBuilder sb = new StringBuilder();
        Vertex vertex = getVertex(user);
        if (vertex != null) {
            LinkedList<Vertex> neighbors = vertex.getNeighbors();
            for (Vertex neighbor : neighbors) {
                sb.append(neighbor.getUser()).append(",");
            }
            
        } else {
            sb.append("Vertex ").append(user).append(" not found.");
        }
        if(!sb.isEmpty())
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    

    //got a problem here
    public String showSecondDegreeConnections(User user) {
        StringBuilder sb = new StringBuilder();
        Vertex vertex = getVertex(user);
        if (vertex != null) {
            LinkedList<Vertex> firstDegreeNeighbors = vertex.getNeighbors();
            
            for (Vertex firstDegreeNeighbor : firstDegreeNeighbors) {
                LinkedList<Vertex> secondDegreeNeighbors = firstDegreeNeighbor.getNeighbors();
                for (Vertex secondDegreeNeighbor : secondDegreeNeighbors) {
                    if (!secondDegreeNeighbor.equals(vertex) && !firstDegreeNeighbors.contains(secondDegreeNeighbor)) {
                        sb.append(secondDegreeNeighbor.getUser()).append(",");
                    }
                }
            }
        } else {
            sb.append("Vertex ").append(user).append(" not found.");
        }
        if(!sb.isEmpty())
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }    
    
    //got a problem also
    public String showFirstAndSecondDegreeConnections(User user) {
        StringBuilder sb = new StringBuilder();
        Vertex vertex = getVertex(user);
        if (vertex != null) {
            LinkedList<Vertex> firstDegreeNeighbors = vertex.getNeighbors();
            for (Vertex firstDegreeNeighbor : firstDegreeNeighbors) {
                sb.append(firstDegreeNeighbor.getUser()).append(",");
                LinkedList<Vertex> secondDegreeNeighbors = firstDegreeNeighbor.getNeighbors();
                for (Vertex secondDegreeNeighbor : secondDegreeNeighbors) {
                    if (!secondDegreeNeighbor.equals(vertex) && !firstDegreeNeighbors.contains(secondDegreeNeighbor)) {
                        sb.append(secondDegreeNeighbor.getUser()).append(" ");
                    }
                }
            }
        } else {
            sb.append("Vertex ").append(user).append(" not found.");
        }
        if(!sb.isEmpty())
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
    
    //working as intended
    public int findDegreeOfConnection(User username1, User username2) {
        Vertex vertex1 = getVertex(username1);
        Vertex vertex2 = getVertex(username2);
    
        if (vertex1 != null && vertex2 != null) {
            LinkedList<Vertex> queue = new LinkedList<>();
            boolean[] visited = new boolean[vertices.size()];
            int[] degree = new int[vertices.size()];
    
            queue.add(vertex1);
            visited[vertex1.getIndex()] = true;
    
            while (!queue.isEmpty()) {
                Vertex current = queue.poll();
    
                if (current.equals(vertex2)) {
                    return degree[current.getIndex()];
                }
    
                LinkedList<Vertex> neighbors = current.getNeighbors();
                int currentDegree = degree[current.getIndex()];
                int nextDegree = currentDegree + 1;
    
                for (Vertex neighbor : neighbors) {
                    int neighborIndex = neighbor.getIndex();
                    if (!visited[neighborIndex]) {
                        queue.add(neighbor);
                        visited[neighborIndex] = true;
                        degree[neighborIndex] = nextDegree;
                    }
                }
            }
        }
    
        return -1; // Return -1 if the connection is not found
    }


    //an exception stack trace output, not the thing that we want
    public String showAdjacency() {
        StringBuilder sb = new StringBuilder();

        for (Vertex vertex : vertices) {
            sb.append(vertex.getUser()).append(":");
            LinkedList<Vertex> neighbors = vertex.getNeighbors();
            for (Vertex neighbor : neighbors) {
                sb.append(neighbor.getUser()).append(",");
            }

            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void storeAdjacency(){
        try{
            FileWriter writer = new FileWriter("connection.txt");
            writer.write(showAdjacency());
            writer.close();
        }catch(IOException e){
            System.out.println("error");
        }
    }

    /* 
    //isolated problem because I don't know the overall algorithm at the moment
    public void loadAdjacency() {
        try (BufferedReader reader = new BufferedReader(new FileReader("connection.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String vertexName = parts[0];
                    String[] neighborNames = parts[1].split(",");
                    User vertex = new User(vertexName, ""); // Create User object for the vertex
                    connectionGraph.addVertex(vertex);
                    for (String neighborName : neighborNames) {
                        User neighbor = new User(neighborName, ""); // Create User object for the neighbor
                        connectionGraph.addVertex(neighbor);
                        connectionGraph.addEdge(vertex, neighbor);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    */

    public boolean vertexExists(User user) {
        for (Vertex vertex : vertices) {
            if (vertex.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    private Vertex getVertex(User user) {
        for (Vertex vertex : vertices) {
            if (vertex.getUser().equals(user)){
                return vertex;
            }
        }
        return null;
    }

    public User getUserByName(String name) {
        for (Vertex vertex : vertices) {
            if (vertex.getUser().getName().equals(name)) {
                return vertex.getUser();
            }
        }
        return null;
    }


    private class Vertex {
        private User user;
        private int index;
        private LinkedList<Vertex> neighbors;

        public Vertex(User user,int index) {
            this.user = user;
            this.index = index;
            this.neighbors = new LinkedList<>();
        }

        public User getUser() {
            return user;
        }

        public int getIndex(){
            return index;
        }

        public LinkedList<Vertex> getNeighbors() {
            return neighbors;
        }

        public void addNeighbor(Vertex neighbor) {
            if (!neighbors.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }

        public void removeNeighbor(Vertex neighbor) {
            neighbors.remove(neighbor);
        }
    }
}

