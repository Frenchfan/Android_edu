package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    //Объявляем экземпляр класса RecyclerView (ранее подключили его
    //через Project Structure - Dependencies - All <odules - Library Dependency
    ArrayList<User> userList = new ArrayList<>();
    //Объявляем коллекцию элементов для вывода на экран
    UserAdapter userAdapter;
    // Объявляем экзмепляр класса адаптера для передачи элементов на наш recycleview
    Button addUSerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //В файле xml основной активности создаем widget recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        //связываем переменную с элементом активности
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        addUSerBtn = findViewById(R.id.addUserBtn);
        addUSerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                startActivity(intent);
            }
        });
    }
    private void recyclerViewInit(){
        Users users = new Users(MainActivity.this);
        //Задаем расположение элементов списка в recycleview - выбираем вариант списка по одному элементу в строке (linear)
        /*for (int i = 0; i < 100; i++) {
            //генерируем список элементов клсса User для recycleview (100 пользователей)
            User user = new User();
            //после вызова конструктора у User появился уникальный UID
            user.setUserName("Пользователь №"+i);
            user.setUserLastName("Фамилия №"+i);
            users.add(user);
            //Добавляем пользователя в коллекцию
        }*/
        userList = users.getUserList();
        userAdapter = new UserAdapter(userList);
        //Создаем экзмепляр класса UserAdapter и передаем в него сгенерированную коллекцию элементов
        recyclerView.setAdapter(userAdapter);
        //Связываем адаптер и recycleView
    }
    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();

    }

    private  class UserHolder extends RecyclerView.ViewHolder{//создаем класс UserHolder на базе ViewHolder
        TextView itemTextView;//Элементы
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {//в описании класса требует создать конструктор
            //в конструктор принимаем 2 аргумента - inflater и viewgroup
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            //раздуваем макет, который представляет собой элемент списка (single_item.xml)
            // itemView - текущий layout single_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User userTest = userAdapter.users.get (getBindingAdapterPosition());
                    //int elementchecked = getBindingAdapterPosition();
                    //String selementchecked = itemTextView.getText().toString();
                    //как выделить элемент?
                    Intent intent = new Intent(MainActivity.this, OnElementClick.class);
                    //intent.putExtra("position", elementchecked);
                    //intent.putExtra("LastName", selementchecked);
                    intent.putExtra("fulluser", (Serializable) userTest);
                    startActivity(intent);
                }
            });
        }

        public void bind(String userString){
            //Метод связывает наш Userholder и userString
            itemTextView.setText(userString);
        }
    }
    // Реализуем класс UserAdapter на базе абстрактного класса RecyclerView.Adapter, в <> указываем
    // Viewholder, который непосредственно генерирует нам элемент списка, далее его тоже переопределим
    //recycleview - отображает на экране, adapter - помещает в список, а viewhoder(userholder тут) генерирвует элементы
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> users;
        //при создании экзмепляра класса ему нужно будет передать коллекцию элементов
        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }




        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            //Recycleviewer вызывает этот
            // метод всякий раз, когда ему нужно создать новый viewHolder
            // i - номер элемента, который он создает
            //viewGroup - то, куда мы будем размещать наш элемент
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            //"раздвувает" наш компонент на activity
            return new UserHolder(inflater, viewGroup);
            //всегда нужно возвращать viewholder (UserHolder)
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            //помещает наши данные в элемент списка
            User user = users.get(position);
            //элемент списка, котторый отображается на экране
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            //создаем строрку из имени и фамилии
            userHolder.bind(userString);
            //связываем holder и нашу коллекцию
        }

        @Override
        public int getItemCount() {
            //возвращает число элементов, которое нужно разместить на экране
            return users.size();
            //получаем размер коллекции элементов, которые мы сгенерировали
        }
    }
}