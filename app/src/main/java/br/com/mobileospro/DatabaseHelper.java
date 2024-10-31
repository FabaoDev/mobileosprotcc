package br.com.mobileospro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mobileOSPro.db";
    private static final int DATABASE_VERSION = 1;

    // Construtor privado
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Declaração de constantes da tabela de peças
    private static final String TABLE_PARTS = "parts";
    private static final String COLUMN_PART_ID = "part_id";
    private static final String COLUMN_PART_NAME = "part_name";
    private static final String COLUMN_PART_QUANTITY = "part_quantity";

    // Declaração de constantes da tabela de usuários
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_ADDRESS = "address";
    private static final String COLUMN_USER_PHONE = "phone";
    private static final String COLUMN_USER_CPF = "cpf";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_TYPE = "user_type";

    // Declaração de constantes da tabela de Clientes
    private static final String TABLE_CLIENTS = "clients";
    private static final String COLUMN_CLIENT_ID = "client_id";
    private static final String COLUMN_CLIENT_NAME = "client_name";
    private static final String COLUMN_CLIENT_PHONE = "client_phone";
    private static final String COLUMN_CLIENT_ADDRESS = "client_address";

    // SQL para criar a tabela de veículos
    private static final String TABLE_VEHICLES = "vehicles";
    private static final String COLUMN_VEHICLE_ID = "vehicle_id";
    private static final String COLUMN_OWNER_NAME = "owner_name";
    private static final String COLUMN_OWNER_MAIL = "owner_mail";
    private static final String COLUMN_OWNER_CPF = "owner_cpf";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_PLATE = "plate";
    private static final String COLUMN_YEAR = "year";

    private static final String CREATE_TABLE_VEHICLES = "CREATE TABLE " + TABLE_VEHICLES + " ("
            + COLUMN_VEHICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_OWNER_NAME + " TEXT, "
            + COLUMN_OWNER_MAIL + " TEXT, "
            + COLUMN_OWNER_CPF + " TEXT, "
            + COLUMN_MODEL + " TEXT, "
            + COLUMN_PLATE + " TEXT, "
            + COLUMN_YEAR + " TEXT)";

    // Declarações de constantes da tabela de ordem de serviço
    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_ORDER_CLIENT = "client_name";
    private static final String COLUMN_ORDER_VEHICLE = "vehicle_model";
    private static final String COLUMN_ORDER_DESCRIPTION = "problem_description";
    private static final String COLUMN_ORDER_SERVICES = "services";
    private static final String COLUMN_ORDER_ESTIMATE = "price_estimate";

    // Declaração de constantes da tabela de ordens de serviço (OS)
    private static final String TABLE_SERVICE_ORDERS = "service_orders";
    private static final String COLUMN_SERVICEORDER_ID = "id";
    private static final String COLUMN_SERVICECLIENT_NAME = "client_name";
    private static final String COLUMN_VEHICLE_MODEL = "vehicle_model";
    private static final String COLUMN_PROBLEM_DESCRIPTION = "problem_description";
    private static final String COLUMN_SERVICES = "services";
    private static final String COLUMN_PRICE_ESTIMATE = "price_estimate";
    private static final String COLUMN_STATUS = "status";

    // Declaração de constantes da tabela de avaliações de serviço
    public static final String TABLE_SERVICE_RATINGS = "service_ratings";
    public static final String COLUMN_RATING_ID = "rating_id";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_COMMENT = "comment";

    // Declaração de constantes da tabela de agendamento de serviços
    private static final String TABLE_SERVICE_SCHEDULE = "service_schedule";
    private static final String COLUMN_SCHEDULE_ID = "id";
    private static final String COLUMN_SCHEDULE_CLIENT_NAME = "client_name";
    private static final String COLUMN_SCHEDULE_VEHICLE_MODEL = "vehicle_model";
    private static final String COLUMN_SERVICE_DATE = "service_date";

    // Declaração de constantes da tabela de funcionários
    private static final String TABLE_EMPLOYEES = "employees";
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_EMPLOYEE_NAME = "employee_name";
    private static final String COLUMN_EMPLOYEE_POSITION = "employee_position";
    private static final String COLUMN_EMPLOYEE_PHONE = "employee_phone";

    // Instância única do DatabaseHelper
    private static DatabaseHelper instance;

    // Método para obter a instância única de DatabaseHelper
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    /// Daqui pra baixo começam as variáveis do projeto

    // Método de criação das tabelas dentro do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Criação das tabelas no banco de dados
        db.execSQL(CREATE_TABLE_VEHICLES); // Criação da tabela vehicles

        // Criação da tabela de usuários dentro do banco de dados
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_ADDRESS + " TEXT, "
                + COLUMN_USER_PHONE + " TEXT, "
                + COLUMN_USER_CPF + " TEXT UNIQUE, "
                + COLUMN_USER_EMAIL + " TEXT UNIQUE, "
                + COLUMN_USER_TYPE + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_USERS);

        //db.execSQL(CREATE_TABLE_CLIENTS); // Criação da tabela clients

        // Método para inserir usuário administrador padrão
        ContentValues values = new ContentValues();
        values.put("email", "admin@mobileospro.com");
        values.put("password", "admin123");  // Senha padrão
        values.put("user_type", "admin");    // Tipo admin
        db.insert(TABLE_USERS, null, values);

        // Método para inserir usuário administrador padrão
        ContentValues valuess = new ContentValues();
        values.put("email", "c@c.com");
        values.put("password", "123456");
        values.put("cpf", "12345678910");
        values.put("user_type", "cliente");
        db.insert(TABLE_USERS, null, valuess);

        // Método para inserir usuário administrador padrão
        ContentValues valuees = new ContentValues();
        values.put("email", "a@a.com");
        values.put("password", "123456");
        values.put("cpf", "12345678911");
        values.put("user_type", "cliente");
        db.insert(TABLE_USERS, null, valuees);

        // Método para inserir usuário administrador padrão
        ContentValues valuues = new ContentValues();
        values.put("email", "b@b.com");
        values.put("password", "123456");
        values.put("cpf", "12345678912");
        values.put("user_type", "funcionario");
        db.insert(TABLE_USERS, null, valuues);

        /*/ SQL para criar a tabela de empregados
        String CREATE_TABLE_EMPLOYEES = "CREATE TABLE " + TABLE_EMPLOYEES + " ("
                + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMPLOYEE_NAME + " TEXT, "
                + COLUMN_EMPLOYEE_POSITION + " TEXT, "
                + COLUMN_EMPLOYEE_PHONE + " TEXT)";
        db.execSQL(CREATE_TABLE_EMPLOYEES);*/

        // SQL para criar a tabela de agendamento de serviços
        String CREATE_TABLE_SERVICE_SCHEDULE = "CREATE TABLE " + TABLE_SERVICE_SCHEDULE + " ("
                + COLUMN_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SCHEDULE_CLIENT_NAME + " TEXT, "
                + COLUMN_SCHEDULE_VEHICLE_MODEL + " TEXT, "
                + COLUMN_SERVICE_DATE + " TEXT)";
        db.execSQL(CREATE_TABLE_SERVICE_SCHEDULE);

        // SQL para criar a tabela de agendamento de serviços
        String CREATE_TABLE_SERVICE_RATINGS = "CREATE TABLE " + TABLE_SERVICE_RATINGS + " ("
                + COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SERVICE_ID + " TEXT, "
                + COLUMN_RATING + " TEXT, "
                + COLUMN_COMMENT + " TEXT)";
        db.execSQL(CREATE_TABLE_SERVICE_RATINGS);

        // SQL para criar a tabela de ordens de serviço
        String CREATE_TABLE_SERVICE_ORDERS = "CREATE TABLE " + TABLE_SERVICE_ORDERS + " ("
                + COLUMN_SERVICEORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SERVICECLIENT_NAME + " TEXT, "
                + COLUMN_VEHICLE_MODEL + " TEXT, "
                + COLUMN_PROBLEM_DESCRIPTION + " TEXT, "
                + COLUMN_SERVICES + " TEXT, "
                + COLUMN_PRICE_ESTIMATE + " TEXT, "
                + COLUMN_STATUS + " TEXT)";

        // SQL para criar a tabela de ordens de serviço
        String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " ("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ORDER_CLIENT + " TEXT, "
                + COLUMN_ORDER_VEHICLE + " TEXT, "
                + COLUMN_ORDER_DESCRIPTION + " TEXT, "
                + COLUMN_ORDER_SERVICES + " TEXT, "
                + COLUMN_ORDER_ESTIMATE + " TEXT)";

        // SQL para criar a tabela de clientes
        String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS + " ("
                + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CLIENT_NAME + " TEXT, "
                + COLUMN_CLIENT_ADDRESS + " TEXT, "
                + COLUMN_CLIENT_PHONE + " TEXT)";

        // SQL para criar a tabela de peças
        String CREATE_TABLE_PARTS = "CREATE TABLE " + TABLE_PARTS + " ("
                + COLUMN_PART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PART_NAME + " TEXT, "
                + COLUMN_PART_QUANTITY + " INTEGER)";

        /*/ Criar tabela de avaliações de serviço
        String CREATE_SERVICE_RATINGS_TABLE = "CREATE TABLE " + TABLE_SERVICE_RATINGS + "("
                + COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SERVICE_ID + " INTEGER, "
                + COLUMN_RATING + " INTEGER, "
                + COLUMN_COMMENT + " TEXT" + ")";
        db.execSQL(CREATE_SERVICE_RATINGS_TABLE);*/

        // Criação da tabela de peças
        String CREATE_PARTS_TABLE = "CREATE TABLE " + TABLE_PARTS + "("
                + COLUMN_PART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PART_NAME + " TEXT, "
                + COLUMN_PART_QUANTITY + " INTEGER" + ")";
        db.execSQL(CREATE_PARTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se o banco de dados for atualizado
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_RATINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_ORDERS);
        onCreate(db);

        /*if (oldVersion < 2) { // Verifica se o banco de dados está em uma versão mais antiga
            // Adiciona a coluna user_type se ela não existir
            db.execSQL("ALTER TABLE USERS ADD COLUMN user_type TEXT NOT NULL DEFAULT 'cliente'");
        }*/
    }

    // Método de cadastro de novo usuário - tela do cliente normal
    public long addUser(String name, String address, String phone, String cpf, String email, String password, String selectedUserType) {
        // Verificar se o email já existe
        if (isCpfOrEmailExistente(cpf, email)) {
            return -1;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_CPF, cpf);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_TYPE, "cliente"); // Define "cliente" como tipo padrão

        return db.insert(TABLE_USERS, null, values);
    }

    // Método para verificar existência de cpf ou email
    public boolean isCpfOrEmailExistente(String cpf, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE cpf = ? OR email = ?", new String[]{cpf, email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Método para verificação de clientes na entrada da tela do login
    public String checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER_TYPE};
        String selection = COLUMN_USER_EMAIL + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String userType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_TYPE));
            cursor.close();
            return userType;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;  // Usuário não encontrado
    }

    // Inserir novo cliente na tela do admin e do funcionário
    public long addClient(String name, String address, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENT_NAME, name); // nome
        values.put(COLUMN_CLIENT_ADDRESS, address);  // email
        values.put(COLUMN_CLIENT_PHONE, phone); // telefone

        return db.insert(TABLE_CLIENTS, null, values);
    }

    // Recuperar todos os clientes
    public Cursor getAllClients() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
    }

    // Método para adicionar uma nova ordem de serviço
    public long addOrder(String client, String vehicle, String description, String services, String estimate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_CLIENT, client);
        values.put(COLUMN_ORDER_VEHICLE, vehicle);
        values.put(COLUMN_ORDER_DESCRIPTION, description);
        values.put(COLUMN_ORDER_SERVICES, services);
        values.put(COLUMN_ORDER_ESTIMATE, estimate);

        return db.insert(TABLE_ORDERS, null, values);
    }

    // Método para recuperar todas as ordens de serviço
    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ORDERS, null);
    }

    // Avaliação do serviço
    public long addServiceRating(String clientName, float rating, String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("client_name", clientName);
        values.put("rating", rating);
        values.put("comments", comments);

        return db.insert("service_ratings", null, values);
    }

    // Método para adicionar um novo veículo
    public long addVehicle(String ownerName, String ownerMail, String ownerCpf, String model, String plate, String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OWNER_NAME, ownerName);
        values.put(COLUMN_OWNER_MAIL, ownerMail);
        values.put(COLUMN_OWNER_CPF, ownerCpf);
        values.put(COLUMN_MODEL, model);
        values.put(COLUMN_PLATE, plate);
        values.put(COLUMN_YEAR, year);

        return db.insert(TABLE_VEHICLES, null, values);
    }

    // Método para recuperar todos os veículos
    public Cursor getAllVehicles() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_VEHICLES, null);
    }

    // Método para adicionar um novo empregado
    public long addEmployee(String name, String position, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NAME, name);
        values.put(COLUMN_EMPLOYEE_POSITION, position);
        values.put(COLUMN_EMPLOYEE_PHONE, phone);

        return db.insert(TABLE_EMPLOYEES, null, values);
    }

    // Método para recuperar todos os empregados
    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EMPLOYEES, null);
    }

    // Método para adicionar uma peça ao estoque
    public long addPart(String partName, String partQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PART_NAME, partName);
        values.put(COLUMN_PART_QUANTITY, partQuantity);

        // Inserir linha no banco de dados
        long result = db.insert(TABLE_PARTS, null, values);
        db.close(); // Fechar o banco de dados
        return result; // Retorna o resultado da operação
    }

    // Método para obter todas as peças do estoque
    public Cursor getAllParts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PARTS, null);
    }

    // Método para obter todas as avaliações de serviço
    public Cursor getAllServiceRatings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICE_RATINGS, null);
    }

    // Método para obter todas as ordens de serviço
    public Cursor getAllServiceOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICE_ORDERS, null);
    }

    // Método para adicionar um agendamento de serviço
    public long addServiceSchedule(String clientName, String vehicleModel, String serviceDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, clientName);
        values.put(COLUMN_VEHICLE_MODEL, vehicleModel);
        values.put(COLUMN_SERVICE_DATE, serviceDate);

        long result = db.insert(TABLE_SERVICE_SCHEDULE, null, values);
        db.close();
        return result;
    }

    // Método para execução do comando de procurar clientes
    public Cursor searchClients(String search) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM clients WHERE name LIKE ? OR cpf LIKE ?";
        String[] args = new String[]{"%" + search + "%", "%" + search + "%"};
        return db.rawQuery(query, args);
    }


}
