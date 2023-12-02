package persistence;

import org.json.JSONObject;

// Persistence package modelled off of JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: saves the object as a JSONObject
    JSONObject toJson();
}
